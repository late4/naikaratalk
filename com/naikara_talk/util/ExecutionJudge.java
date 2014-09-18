package com.naikara_talk.util;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * 実行判定処理
 * シングルトンで実装されます。
 * 実行にはコード管理マスタキャッシュから、XMLファイルのパスを取得します。
 * ファイルパスはコード管理マスタ"S10","0001"で定義されています。
 * XMLパスはaction要素に定義されたrole要素で定義されます。
 *
 * @version 1.0
 */
public class ExecutionJudge {

	private static ExecutionJudge instance;
	private Map<String, List<String>> authMap = new HashMap<String, List<String>>();

	/**
	 * デフォルトのコンストラクタを隠蔽
	 * @throws NaiException
	 */
	private ExecutionJudge() throws NaiException{
		init();
	}

	/**
	 * このクラスの唯一のインスタンスを取得します。
	 * @return
	 * @throws NaiException
	 */
	public static ExecutionJudge getInstance() throws NaiException {
	    if (instance == null) {
	    	instance = new ExecutionJudge();
	    }
	    return instance;
	}


	/**
	 * 実行判定
	 * 定義ファイルから作成したマップに該当すれば実効許可を出します。
	 * @param action
	 * @param role
	 * @return 判定結果
	 */
	public boolean judge(String action, String role){

		List<String> list = authMap.get(action);

		return list != null && list.contains(role) ? true : false;

	}


	/**
	 * 一応再読み込み用の処理も入れて起きます。
	 * @throws NaiException
	 */
	public void reload() throws NaiException {
		init();
	}

	/**
	 * xmlの読み込みと初期化
	 * @throws NaiException
	 */
	private void init() throws NaiException {
		DocumentBuilderFactory factory;
		DocumentBuilder        builder;
		Node root;
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setIgnoringComments(true);
			factory.setValidating(true);
			root = builder.parse(
					CodeManagMstCache.getInstance().getEntity(
										NaikaraTalkConstants.EXEC_JUDGE_XML_PATH
										,"0001").getManagerNm());


			loadXml(root);
		} catch (ParserConfigurationException e0) {
			throw new NaiException(e0);
		} catch (SAXException e1){
			throw new NaiException(e1);
		} catch (IOException e2) {
			throw new NaiException(e2);
		} catch (NaiException e) {
			throw e;
		}
	}

	/**
	 * ノードを検索してactionクラスのマップを作成します。
	 * マップの値は許可されるロールの配列です。
	 * @param node
	 */
	public void loadXml(Node node) {

		NodeList nodes = node.getChildNodes();

		for (int i=0; i<nodes.getLength(); i++) {
			Node node2 = nodes.item(i);

			if (StringUtils.equals(node2.getNodeName(), "action")) {

				System.out.println( "action = " + node2.getAttributes().getNamedItem("name"));

				//無かったら作る
				if(authMap.get("name") == null) {
					authMap.put(node2.getAttributes().getNamedItem("name").getNodeValue(), new ArrayList<String>());
				}

				NodeList nodeRoles = node2.getChildNodes();

				// actionノード内のループ
				for(int j=0; j < nodeRoles.getLength(); j++) {

					Node nodeRole = nodeRoles.item(j);
					if (StringUtils.equals(nodeRole.getNodeName(), "role")) {
						authMap.get(node2.getAttributes().getNamedItem("name").getNodeValue()).add(nodeRole.getTextContent());
					}
				}
			} else {
				// 再帰的にパスを探す
				loadXml(node2);
			}
		}
	}
}
