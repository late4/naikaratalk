package com.naikara_talk.opentok_beta;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>カメラ起動確認テスト画面<br>
 * <b>クラス名称 :</b>カメラ起動確認テストTOKBOX接続用<br>
 * <b>クラス概要 :</b>カメラ起動確認テストTOKBOX接続用<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴 :</b>2014/01/15 TECS 新規作成
 */
public class ArchiveHelper {

	private static WebResource service1, service2, service3;
	// private static String archiveId;
	private static String API_KEY;
	private static String API_SECRET;
	private static boolean archiveExists;
	private static int status;
	private static String responseString;
	private static ClientResponse clientResponse;

	public ArchiveHelper(String API_KEY, String API_SECRET) {
		ArchiveHelper.API_KEY = API_KEY;
		ArchiveHelper.API_SECRET = API_SECRET;

	}

	public boolean createClient(String command, String sessionId,
			String archiveId) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		if (command.equals("start") || command.equals("listall")) {
			service1 = client.resource(getBaseURIArchiving());
			return true;
			// startArchiving(command, sessionId);
		}
		if ((command.equals("stop") || command.equals("list") || command
				.equals("delete"))) {
			if (getBaseURIArchiving().equals(null)) {
				System.out.println("returned url is null");
			}
			String sURI = getBaseURIArchiving().toString().concat(archiveId);
			try {
				service2 = client.resource(new URI(sURI));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	// TOKBOXにアクセス用URLを用意する
	private static URI getBaseURIArchiving() {
		return UriBuilder.fromUri(
				"https://api.opentok.com/v2/partner/" + API_KEY + "/archive/")
				.build();
	}

	// SHould this return something instead?
	@SuppressWarnings("deprecation")
	public void startArchiving(String sessionId, String name) {
		System.out.println("Start Archiving");
		JSONObject archiveObject = null;
		JSONObject response = null;
		try {
			archiveObject = new JSONObject();

			// archiveObject.put("sessionId","1_MX4xMDB-fk1vbiBEZWMgMDIgMTY6NDI6MTkgUFNUIDIwMTN-MC44MzE3NTg5fg");
			archiveObject.put("sessionId", sessionId);
			archiveObject.put("action", "start");
			archiveObject.put("name", name);
			System.out.println(archiveObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			response = service1.accept("application/json")
					.type("application/json")
					.header("X-TB-PARTNER-AUTH", API_KEY + ":" + API_SECRET)
					.post(JSONObject.class, archiveObject);
			// clientResponse1 =
			// service1.accept("application/json").get(ClientResponse.class);
			System.out.println("response is " + response);
		} catch (UniformInterfaceException uie) {
			status = uie.getResponse().getStatus();

			switch (status) {
			case 400:
				responseString = "Invalid SessionId or invalid action or client not connected to OT session";
				break;
			case 403:
				responseString = "Invalid API_KEY or PARTNER_SECRET";
				break;
			case 404:
				responseString = "Session does not exist";
				break;
			case 409:
				responseString = "Session already being recorded or you are attempting to record p2p session";
				break;
			case 500:
				responseString = "OpenTok Error";
				break;
			}

			System.out.println(responseString);
		}

	}

	// Handle responses appropriately- like 403, 204 etc.
	public void stopArchiving(String sessionId, String archiveId) {
		JSONObject object = null;
		try {
			object = new JSONObject();
			// object.put("sessionId","1_MX4xMDB-fk1vbiBEZWMgMDIgMTY6NDI6MTkgUFNUIDIwMTN-MC44MzE3NTg5fg");
			object.put("sessionId", sessionId);
			object.put("action", "stop");
			// object.put("name", "archive1");
			object.put("name", archiveId);
			System.out.println(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			JSONObject response = service2.accept("application/json")
					.type("application/json")
					.header("X-TB-PARTNER-AUTH", API_KEY + ":" + API_SECRET)
					.post(JSONObject.class, object);
			System.out.println(response);
		} catch (UniformInterfaceException uie) {
			status = uie.getResponse().getStatus();
			switch (status) {
			case 400:
				responseString = "Invalid SessionId or invalid action or client not connected to OT session";
				break;
			case 403:
				responseString = "Invalid API_KEY or PARTNER_SECRET";
				break;
			case 404:
				responseString = "Session does not exist";
				break;
			case 409:
				responseString = "Attempting to start an archive not currently recorded";
				break;
			case 500:
				responseString = "OpenTok Error";
				break;
			}
			System.out.println(responseString);
		}
	}

	public void listAllArchives(String sessionId) {
		try {
			JSONObject response = service1.accept("application/json")
					.type("application/json")
					.header("X-TB-PARTNER-AUTH", API_KEY + ":" + API_SECRET)
					.get(JSONObject.class);
			System.out.println(response);
		} catch (UniformInterfaceException uie) {
			status = uie.getResponse().getStatus();
			switch (status) {
			case 403:
				responseString = "Invalid API_KEY or PARTNER_SECRET";
				break;
			case 404:
				responseString = "Session does not exist";
				break;
			case 500:
				responseString = "OpenTok Error";
				break;
			}
			System.out.println(responseString);
		}
		// System.out.println(responseString);

	}

	// No need for command to be sent
	public JSONObject listArchive(String sessionId, String archiveId) {
		JSONObject response = null;
		try {
			response = service2.accept("application/json")
					.type("application/json")
					.header("X-TB-PARTNER-AUTH", API_KEY + ":" + API_SECRET)
					.get(JSONObject.class);
			System.out.println(response);

		} catch (UniformInterfaceException uie) {
			status = uie.getResponse().getStatus();
			switch (status) {
			case 400:
				responseString = "No SessionId or invalid archive id";
				break;
			case 403:
				responseString = "Invalid API_KEY or PARTNER_SECRET";
				break;
			case 404:
				responseString = "Session does not exist";
				break;
			case 500:
				responseString = "OpenTok Error";
				break;
			case 200:
				responseString = "Successfully listed archive";
				break;
			}
			System.out.println(responseString);
		}
		return response;
	}

	public void deleteArchive(String sessionId, String archiveId) {
		ClientResponse response = null;

		try {
			response = service2.header("X-TB-PARTNER-AUTH",
					API_KEY + ":" + API_SECRET).delete(ClientResponse.class);
		} catch (UniformInterfaceException uie) {
			status = uie.getResponse().getStatus();
			switch (status) {
			case 403:
				responseString = "Invalid API_KEY or PARTNER_SECRET";
				break;
			case 500:
				responseString = "OpenTok Error";
				break;

			}
			System.out.println(responseString);
			// System.out.println("Client response code is " +
			// response.getClientResponseStatus().getStatusCode());
			// if(service3.head().getClientResponseStatus().getStatusCode() ==
			// 204){
			// System.out.println("Archive Deleted");
			// }
		}
		if (response.getClientResponseStatus().getStatusCode() == 204) {
			System.out.println("Deleted Archive" + response);
		}
	}
}
