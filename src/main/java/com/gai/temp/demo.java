package com.gai.temp;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void merge(int A[], int m, int B[], int n) {
        int right = A.length - 1;
        int curA = m - 1;
        int curB = n - 1;
        while(curA >= 0 && curB >= 0){
            if(A[curA] > B[curB]){
                A[right--] = A[curA--];
            }else {
                A[right--] = B[curB--];
            }
        }
        while(curA >= 0){
            A[right--] = A[curA--];
        }
        while(curB >= 0){
            A[right--] = B[curB--];
        }
    }

    public static String replace(String A,String B ,String C){
        StringBuffer str = new StringBuffer();
        char[] charA = A.toCharArray();
        char[] charB = B.toCharArray();
        int indexA = 0;
        int indexB = 0;
        boolean flag = true;
        while (indexA < A.length()){
            while (indexA < A.length() && indexB < B.length() && charA[indexA] == charB[indexB]){
                indexA++;
                indexB++;
            }
            if(flag && indexB == B.length()){
                str.append(C);
                flag = false;
                continue;
            } else if(flag) {
                indexA = indexA - indexB;
                indexB = 0;
            }
            str.append(charA[indexA]);
            indexA++;
        }
        return str.toString();
    }

    public static int findKth(int[] a, int n, int K) {
        // write code here
        return partition(a,0,n-1,K);
    }

    public static int partition(int[] a,int low,int high,int K){
        int start = low;
        int end = high;
        int key = a[low];

        while(end > start){
            while(end > start && a[end] <= key){
                end--;
            }
            if(a[end] > key) exec(a,start,end);
            while(end > start && a[start] >= key){
                start++;
            }
            if(a[start] < key) exec(a,start,end);
        }

        if(start > low && start > K-1) return partition(a,low,start-1,K);
        if(end < high && end < K-1) return partition(a,end + 1,high,K);

        return a[start];
    }

    private static void exec(int[] a,int low,int high){
        int temp = a[low];
        a[low] = a[high];
        a[high] = temp;
    }

    public static int mySqrt(int x) {
        int l = 0,r = x;
        int res = -1;

        while(l <= r){
            int mid = l + (r-l) / 2;
            long cul = (long) mid * mid;
            if(cul <= x){
                res = mid;
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }

        return res;
    }

    public static int binarySearch(int[] array, double k) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (array[mid] <= k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

    public static void main(String[] args) {
        System.out.println(7%6);
    }
}
