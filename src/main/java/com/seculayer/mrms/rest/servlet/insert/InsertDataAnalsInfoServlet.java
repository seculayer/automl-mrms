package com.seculayer.mrms.rest.servlet.insert;

import com.seculayer.mrms.common.Constants;
import com.seculayer.mrms.managers.MRMServerManager;
import com.seculayer.mrms.rest.ServletFactory;
import com.seculayer.mrms.rest.ServletHandlerAbstract;
import com.seculayer.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertDataAnalsInfoServlet extends ServletHandlerAbstract {
    public static final String ContextPath = ServletHandlerAbstract.ContextPath + "/insert_data_anls_info";

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        PrintWriter out = httpServletResponse.getWriter();

        logger.debug("###################################################################");
        logger.debug("In doPost - insert data analysis info");

        try {
            Map<String, Object> map = ServletFactory.getBodyFromJSON(httpServletRequest);
            Map<String, Object> rstMap = this.readDaResult(map);
            commonDAO.insertDataAnlsInfo(rstMap);
            logger.debug(rstMap.toString());

            map.put("status_cd", Constants.STATUS_DA_COMPLETE);
            commonDAO.updateDAStatus(map);

            out.println("1");
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            out.println("error");
        }

        logger.debug("###################################################################");
    }

    private Map<String, Object> readDaResult(Map<String, Object> map) {
        Map<String, Object> rstMap = new HashMap<>();
        String fileName = String.format("DA_META_%s.info", map.get("dataset_id").toString());
        File file = new File(MRMServerManager.getInstance().getConfiguration().get("ape.features.dir"), fileName);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()))){
            Map<String, Object> daResult = JsonUtil.getMapFromString(reader);
            rstMap.put("dataset_id", map.get("dataset_id").toString());
            rstMap.put("metadata_json", daResult);
            rstMap.put("analysis_file_nm", fileName);
            rstMap.put("dist_file_cnt", ((List<?>) daResult.get("file_list")).size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rstMap;
    }
}
