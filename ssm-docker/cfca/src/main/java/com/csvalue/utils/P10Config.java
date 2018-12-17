package com.csvalue.utils;

import cfca.kt.toolkit.ClientContext;
import cfca.kt.vo.KeyPairRequestVO;
import cfca.kt.vo.KeyPairResponseVO;
import cfca.kt.vo.util.XmlUtil2;
import com.csvalue.common.PropertiesUtils;
import com.mysql.jdbc.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.LoggerFactory;
import java.util.Iterator;

public class P10Config {
    final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    public static ClientContext getCFCARAClientContext() {
        String ip=PropertiesUtils.getProperty("p10.ip");
        int port=Integer.parseInt(PropertiesUtils.getProperty("p10.port"));

        int connectTimeout = Integer.parseInt(PropertiesUtils.getProperty("p10.connectTimeout"));
        int readTimeout = Integer.parseInt(PropertiesUtils.getProperty("p10.readTimeout"));

        ClientContext client = null;
        ClientContext.initSocket(ip, port, connectTimeout, readTimeout);

        client = ClientContext.getInstance();
        return client;
    }

    /* 获取p10
     * 生成密钥对、返回CSR(P10)和密钥标识
     * */
    public String getP10() {
        ClientContext context = P10Config.getCFCARAClientContext();
        String requestXml = null;
        String responseXml = null;
        String p10="";
        try {
            KeyPairRequestVO requestVO = new KeyPairRequestVO();
            requestVO.setTxType("xxxx");
            KeyPairResponseVO responseVO = null;
            requestXml = XmlUtil2.vo2xml(requestVO, "Request");
            responseVO = context.tx1001(requestVO);
            //xml响应结果
            responseXml = XmlUtil2.vo2xml(responseVO, "Response");
            if(responseXml!=null){
                p10=getXmlNode(responseXml);
            }else{
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("请求报文:"+"\n",requestXml);
        log.info("响应报文:"+"\n",responseXml);
        System.out.println("请求报文" + "\n" + requestXml);
        System.out.println("响应报文" + "\n" + responseXml);
        return p10;
    }

    /*
     * 获取xml节点值
     * */
    public String getXmlNode(String xml){
        Document doc = null;
        String ResultCode="";
        String Csr="";
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            Iterator iter = rootElt.elementIterator("Head"); // 获取根节点下的子节点head
            // 遍历head节点
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                ResultCode = recordEle.elementTextTrim("ResultCode"); // 拿到head节点下的子节点title值
                log.info("ResultCode:",ResultCode);
                System.out.println(ResultCode);
            }
            Iterator iterator = rootElt.elementIterator("Body"); // /获取根节点下的子节点body
            // 遍历body节点
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                Csr = element.elementTextTrim("Csr"); // 拿到body节点下的子节点Csr值
                log.info("Csr:",Csr);
                System.out.println("Csr:" + Csr);
            }
        } catch (DocumentException e) {
            log.error("getXmlNode异常："+e.getMessage());
        } catch (Exception e) {
            log.error("getXmlNode异常："+e.getMessage());
        }
        if("2000".equals(ResultCode) && !StringUtils.isNullOrEmpty(Csr)){
            return Csr;
        }else{
            return "error";
        }
    }

//    public static void main(String[] args) throws DocumentException {
//        Map map = new HashMap();
//        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
//                "<Response>\n" +
//                "<Head>\n" +
//                "<TxType>1001</TxType>\n" +
//                "<TxTime>2018/09/18 17:42:01</TxTime>\n" +
//                "<ResultCode>2000</ResultCode>\n" +
//                "<ResultMessage>OK</ResultMessage>\n" +
//                "</Head>\n" +
//                "<Body>\n" +
//                "<Csr>MIICUjCCAToCAQAwDzENMAsGA1UEAwwEQ0ZDQTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAN6Lw29OulJZ6B6WnlbcB1v+MI4sFgQdz2cADt2shgLewq8gO/aW2if4JfSPYTE5ih2udxK/gSNPcHhGg16NuRXV3hifsfTfEa1q3BfLgwd1NvwFCwPtGz8cmZ5VMzwIp9y234FObF0+ehjqkKOWdlghLX7azQO4pqWfYkSWJueKvGDRJcmMN2YULLkcLn3MaD0ebIcTunNNEPyiMAYNoCxuMk343Fjo4vlj35mO9BJkm1m7tSwEpGX4lMMDksotepSMA4xf7PtQFz6klaGNcLiyXqx1DakJjEdo64ro7iw+SDINEOMFwGS4Jk9AmQ8tuImzX2+JN+UQjVX+Np5DeEECAwEAATANBgkqhkiG9w0BAQUFAAOCAQEAd3xWd6st2BZnWLTaXzPZT4QZXl3AK5WEqhti6GJA7EfCzbE8E7TG65B4L+tKTNcTR5SeJt0xg0aJfGTcRKvuW+I421KQMDMK6bergwlxQuNwupUQSXGQCCmZaPdfQHmshebsrNPR2vQYczwcSRHHXeuXnzu28eNjF1ameNVvS8Bh4zQJALXY/HaCcmqwfYalC2b7RevtjbSmeDCp5d2ci8pm/HH9MvBltH9vNHD1unb1nUWG1lhbFaneLrgP94/8NyJULessM9VEDiyGXLHn/1oj8dghCNfJ/kjAv4+Zy3sn6Nzh33EumA1Lq0jsRain0pzhB8S/HTXrorsFSJqTLw==</Csr>\n" +
//                "<KeyIdentifier>180918174201147073</KeyIdentifier>\n" +
//                "</Body>\n" +
//                "</Response>\n";
//        Document doc = null;
//        doc = DocumentHelper.parseText(xml); // 将字符串转为XML
//        Element rootElt = doc.getRootElement(); // 获取根节点
//        System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
//        Iterator iter = rootElt.elementIterator("Head"); // 获取根节点下的子节点head
//        // 遍历head节点
//        while (iter.hasNext()) {
//            Element recordEle = (Element) iter.next();
//            String ResultCode = recordEle.elementTextTrim("ResultCode"); // 拿到head节点下的子节点title值
//            System.out.println(ResultCode);
//        }
//
//        Iterator iterss = rootElt.elementIterator("Body"); // /获取根节点下的子节点body
//        // 遍历body节点
//        while (iterss.hasNext()) {
//            Element recordEless = (Element) iterss.next();
//            String Csr = recordEless.elementTextTrim("Csr"); // 拿到body节点下的子节点result值
//            System.out.println("Csr:" + Csr);
//            Iterator itersElIterator = recordEless.elementIterator("form"); // 获取子节点body下的子节点form
//        }
//    }
}
