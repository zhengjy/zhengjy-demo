package com.zhengjy;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by Jiyang.Zheng on 2019/2/19 9:37.
 */
public class SqlHandel {


    public static String sqlHandle(String sql,Map<String,String> param ){
        String retSql = sql;
        // search open token
        int start = sql.indexOf("#{");
        if (start == -1) {
            return sql;
        }
        while (start > -1) {
            int openTokenIndex = start+2;
            String sqlTemp = sql.substring(openTokenIndex,sql.length());
            int closeTokenIndex = sqlTemp.indexOf("}");
            //#{}值
            String value = sql.substring(openTokenIndex,openTokenIndex+closeTokenIndex);
            String mapVal = param.get(value);
            if (StringUtils.isEmpty(mapVal)){
                if (sql.indexOf("where") > -1){
                    int whereIndexStart = sql.indexOf("where");
                    int whereIndexEnd = sql.indexOf("}")+1;
                    String sqlStart = sql.substring(0,whereIndexStart);
                    String sqlEnd = sql.substring(whereIndexEnd,sql.length());
                    retSql = sqlStart +" where 1=1 " + sqlEnd;
                }else {
                    int andIndexStart = sql.indexOf("and");
                    int andIndexEnd = sql.indexOf("}")+1;
                    String sqlStart = sql.substring(0,andIndexStart);
                    String sqlEnd = sql.substring(andIndexEnd,sql.length());
                    retSql = sqlStart + sqlEnd;
                }
            }
            start = retSql.indexOf("#{");
        }

        return retSql;
    }

//    public String handle(Map<String,String> param){
//        int openTokenIndex = sql.indexOf("#{")+2;
//
//        String sqlTemp = sql.substring(openTokenIndex,sql.length());
//        int closeTokenIndex = sqlTemp.indexOf("}");
//
//        //#{}值
//        String value = sql.substring(openTokenIndex,openTokenIndex+closeTokenIndex);
//        String mapVal = param.get(value);
//        if (StringUtils.isEmpty(mapVal)){
//            if (sql.indexOf("where") > -1){
//                int whereIndexStart = sql.indexOf("where");
//                int whereIndexEnd = sql.indexOf("}")+1;
//                String sqlStart = sql.substring(0,whereIndexStart);
//                String sqlEnd = sql.substring(whereIndexEnd,sql.length());
//                return sqlStart +" where 1=1 " + sqlEnd;
//            }else {
//                int andIndexStart = sql.indexOf("and");
//                int andIndexEnd = sql.indexOf("}")+1;
//                String sqlStart = sql.substring(0,andIndexStart);
//                String sqlEnd = sql.substring(andIndexEnd,sql.length());
//
//                return sqlStart + sqlEnd;
//            }
//        }
//        return sql;
//    }
}
