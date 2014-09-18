package com.naikara_talk.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class BeanUtils {

    /**
     * copyBeanToBean。
     *
     * @param
     * @return なし
     * @throws なし
     */
    public static void copyBeanToBean(Object srcBean, Object desBean) {

        Class<?> srcCls = srcBean.getClass();
        Method[] srcMethods = srcCls.getDeclaredMethods();
        Field[] srcFields = srcCls.getDeclaredFields();
        Class<?> desCls = desBean.getClass();
        Method[] desMethods = desCls.getDeclaredMethods();
        Field[] desFields = desCls.getDeclaredFields();
        for (Field field : srcFields) {
            try {
                String fieldGetMethodName = createGetMethodName(field.getName());
                String fieldSetMethodName = createSetMethodName(field.getName());
                if (!hasMethod(srcMethods, fieldGetMethodName)) {
                    continue;
                }
                if (!hasMethod(desMethods, fieldSetMethodName)) {
                    continue;
                }
                if (!fieldTypeCheck(desFields, field)) {
                	StringBuffer sb = new StringBuffer();
                	sb = sb.append("invalid field type : ").append(field.getName());
                    throw new Exception(sb.toString());
                }
                Method getMethod = srcCls.getDeclaredMethod(fieldGetMethodName);
                Method setMethod = desCls.getDeclaredMethod(fieldSetMethodName,
                        field.getType());
                setMethod.invoke(desBean, getMethod.invoke(srcBean));
            } catch (Exception e) {
                // log出力変更予定
                e.printStackTrace();
            }
        }

    }

    /**
     * copyMapToBean。
     *
     * @param
     * @return なし
     * @throws なし
     */
    public static void copyMapToBean(Map<String, String> valMap, Object bean) {
        Class<?> cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldSetMethodName = createSetMethodName(field.getName());
                if (!hasMethod(methods, fieldSetMethodName)) {
                    continue;
                }
                Method setMethod = cls.getMethod(fieldSetMethodName,
                        field.getType());
                String value = valMap.get(field.getName());
                if (!StringUtils.isEmpty(value)) {
                    String fieldType = field.getType().getSimpleName();
                    if ("String".equals(fieldType)) {
                        setMethod.invoke(bean, value);
                    } else if ("Timestamp".equals(fieldType)) {
                        setMethod.invoke(bean, value);
                    } else if ("Integer".equals(fieldType)
                            || "int".equals(fieldType)) {
                        Integer intval = Integer.parseInt(value);
                        setMethod.invoke(bean, intval);
                    } else if ("Long".equalsIgnoreCase(fieldType)) {
                        Long temp = Long.parseLong(value);
                        setMethod.invoke(bean, temp);
                    } else if ("Double".equalsIgnoreCase(fieldType)) {
                        Double temp = Double.parseDouble(value);
                        setMethod.invoke(bean, temp);
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                        Boolean temp = Boolean.parseBoolean(value);
                        setMethod.invoke(bean, temp);
                    } else if ("BigDecimal".equalsIgnoreCase(fieldType)) {
                        Boolean temp = Boolean.parseBoolean(value);
                        setMethod.invoke(bean, temp);
                    } else {
                        StringBuffer sb = new StringBuffer();
                        sb = sb.append("not supper type").append(fieldType);
                        System.out.println(sb.toString());
                    }
                }
            } catch (Exception e) {
                // log出力変更予定
                e.printStackTrace();
            }
        }
    }

    /**
     * copyBeanToMap。
     *
     * @param
     * @return なし
     * @throws なし
     */
    public static void copyBeanToMap(Object bean, Map<String, Object> map) {
        Class<?> cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldGetMethodName = createGetMethodName(field.getName());
                if (!hasMethod(methods, fieldGetMethodName)) {
                    continue;
                }
                Method getMethod = cls.getMethod(fieldGetMethodName);
                map.put(field.getName(), getMethod.invoke(bean));
            } catch (Exception e) {
                // log出力変更予定
                e.printStackTrace();
            }
        }
    }

    /**
     * hasMethod。
     *
     * @param
     * @return なし
     * @throws なし
     */
    private static boolean hasMethod(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * fieldTypeCheck。
     *
     * @param
     * @return なし
     * @throws なし
     */
    private static boolean fieldTypeCheck(Field[] fields, Field srcfield) {
        for (Field field : fields) {
            if (field.getName().equals(srcfield.getName())) {
                return field.getType().getSimpleName()
                        .equals(srcfield.getType().getSimpleName());
            }
        }
        return false;
    }

    /**
     * createGetMethodName。
     *
     * @param
     * @return なし
     * @throws なし
     */
    private static String createGetMethodName(String fieldName) {
        if (StringUtils.isEmpty(fieldName)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb = sb.append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
        return sb.toString();
    }

    /**
     * createSetMethodName。
     *
     * @param
     * @return なし
     * @throws なし
     */
    private static String createSetMethodName(String fieldName) {
        if (StringUtils.isEmpty(fieldName)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb = sb.append("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
        return sb.toString();
    }
}
