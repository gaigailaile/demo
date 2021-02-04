package com.gai.temp;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class demo {
//    public static void main(String[] args) {
//        String sql = "select employee_id, max(cast(organization_id as integer)) \n" +
//                "organization_id \n" +
//                "from src_ht_hr_ims.ims_rt_employee_organization where state=1\n" +
//                "and jobtitle <> '--aaa' --and employee_id='50019940'\n" +
//                "group by employee_id  ";
//        Pattern p = Pattern.compile("(?ms)('(?:''|[^'])*')|--.*?$|/\\*.*?\\*/|#.*?$|");
//        Pattern p1 = Pattern.compile("(?ms)('(?:''|[^'])*')|--.*?$");
//        System.out.println(p1.matcher(sql).replaceAll("$1"));
//
//
////        String result = p.matcher(sql).replaceAll("$1");
////        System.out.println(result);
//    }

//    public static void main(String[] args) {
//        String line = " select * from users;      select * from user where name = \"gaidongxu;\"; ";
////        String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//        String[] tokens = line.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
//        for(String t : tokens) {
//            System.out.println("> "+t);
//        }
//        Arrays.asList(tokens).stream().filter(s -> !StringUtils.isBlank(s)).map(s -> s.trim()).forEach(s -> System.out.println("list > " + s));
//    }

//    public static void main(String[] args) throws JSQLParserException {
//        String sql = "SELECT * FROM home WHERE users_id in (SELECT id FROM users)";
//        Statement statement = CCJSqlParserUtil.parse(sql);
//        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
//        List list = tablesNamesFinder.getTableList(statement);
////        list.stream().forEach(s->System.out.println("---------------" + s));
//        List<String> a = Arrays.asList("22222","111111","home");
//        list.addAll(a);
//        list = (List) list.stream().distinct().collect(Collectors.toList());
//        list.stream().forEach(s->System.out.println("---------------" + s));
////        Pattern p = Pattern.compile("(\\s+from\\s+(?:\\w+\\.)*\\w+\\s*\\w*\\s*,\\s*(?:\\w+\\.)*\\w+)|(\\s+from\\s+(?:\\w+\\.)*\\w+)|(\\s+join\\s+(?:\\w+\\.)*\\w+\\s+(?:\\w+\\s)*\\s*on\\s+)");
////        Matcher m = p.matcher(sql);
////        while (m.find()) {
////            System.out.println(m.group());
////        }
//
//    }

//    public static void main(String[] args) throws IOException {
//        String charSet = null;
//        String filePath = "/Users/gaidongxu/t_da_dst_rule.sql";
//        if (charSet == null) {
//            charSet = "utf-8";
//        }
//        File file = new File(filePath);
//        if (!file.exists() || !file.isFile()) {
//            throw new RuntimeException("执行文件不存在！");
//        }
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                new FileInputStream(filePath), charSet));
//        StringBuffer contentBuffer = new StringBuffer(5120);
//        String temp = null;
//        while ((temp = br.readLine()) != null) {
//            //可以在这里处理注释相关内容
//            contentBuffer.append(temp);
//        }
//        temp = contentBuffer.toString();
//        //无换行符
//        temp = new String(temp.replaceAll("\\s{2,}", " "));
//        System.out.println(temp);
//    }

    public static void main(String[] args) {
        String a = "\n";
        String[] a2 = a.split("\n");
        for (String name:a2) {
            System.out.println(name);
        }
    }
}
