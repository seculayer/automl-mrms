package com.seculayer.mrms.request;

//import com.seculayer.ape.mlms.info.DetectInfo;
//import com.seculayer.ape.mlms.info.DetectInfoAbstract;
//import com.seculayer.ape.mlms.info.LearnInfo;
//import com.seculayer.ape.mlms.info.VerifyInfo;

import com.seculayer.mrms.db.CommonDAO;
import com.seculayer.mrms.db.ProjectManageDAO;
import com.seculayer.mrms.info.DAInfo;
import com.seculayer.mrms.info.RcmdInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public class RequestUtils {
    protected static Logger logger = LogManager.getLogger();

    private static final CommonDAO dao = new CommonDAO();
    private static final ProjectManageDAO projectDao = new ProjectManageDAO();

    public static DAInfo createDAInfo(Map<String, Object> schd) throws IOException {
        Map<String, Object> datasetInfo = dao.selectDatasetInfo(schd.get("dataset_id").toString());

        DAInfo daCheifInfo = new DAInfo(schd.get("dataset_id").toString());
        daCheifInfo.init(datasetInfo);
        logger.debug(daCheifInfo.toString());

        return daCheifInfo;
    }

    public static RcmdInfo createRcmdInfo(Map<String, Object> schd) throws IOException {
        RcmdInfo rcmdInfo = new RcmdInfo(schd.get("project_id").toString());
        rcmdInfo.init(schd);
        logger.debug(rcmdInfo.toString());

        return rcmdInfo;
    }

//    public static LearnInfo createLearnInfo(Map<String, Object> schedule, String key) throws IOException {
//        LearnInfo learnInfo = new LearnInfo(key);
//
//        learnInfo.init(schedule);
//        learnInfo.makeDataset(commonDAO.selectDatasetList(schedule));
//        learnInfo.setAlgorithms(schedule);
//        learnInfo.setNumWorker();
//        learnInfo.writeInfo();
//        logger.info(learnInfo.toString());
//
//        return learnInfo;
//    }
//
//    public static LearnInfo loadLearnInfo(String key){
//        logger.debug("{}.info is exist.", key);
//        LearnInfo learnInfo = new LearnInfo();
//        learnInfo.loadInfo(key);
//        return learnInfo;
//    }
//
//
//    public static DetectInfo getDetectInfo(String key, Map<String, Object> schedule) throws IOException {
//        DetectInfo detectInfo;
//        if (DetectInfoAbstract.isExist(key)) {
//            detectInfo = com.seculayer.ape.mlms.request.RequestUtils.loadDetectInfo(key);
//        } else {
//            detectInfo = com.seculayer.ape.mlms.request.RequestUtils.createDetectInfo(schedule, key);
//        }
//        return detectInfo;
//    }
//
//    public static DetectInfo createDetectInfo(Map<String, Object> schedule, String key) throws IOException {
//        DetectInfo detectInfo = new DetectInfo(key);
//
//        detectInfo.init(schedule);
//        detectInfo.setModelsAndFields(schedule);
//        detectInfo.writeInfo();
//        logger.info(detectInfo.toString());
//        return detectInfo;
//    }
//
//    public static DetectInfo loadDetectInfo(String key){
//        logger.debug("{}.info is exist.", key);
//        DetectInfo detectInfo = new DetectInfo();
//        detectInfo.loadInfo(key);
//        return detectInfo;
//    }
//
//    public static VerifyInfo getVerifyInfo(String key, Map<String, Object> schedule) throws IOException {
//        VerifyInfo verifyInfo;
//        if (DetectInfoAbstract.isExist(key)) {
//            verifyInfo = com.seculayer.ape.mlms.request.RequestUtils.loadVerifyInfo(key);
//        } else {
//            verifyInfo = com.seculayer.ape.mlms.request.RequestUtils.createVerifyInfo(schedule, key);
//        }
//        return verifyInfo;
//    }
//
//    public static VerifyInfo createVerifyInfo(Map<String, Object> schedule, String key) throws IOException {
//        VerifyInfo verifyInfo = new VerifyInfo(key);
//        verifyInfo.init(schedule);
//        verifyInfo.setNumWorker();
//        verifyInfo.writeInfo();
//        logger.info(verifyInfo.toString());
//        return verifyInfo;
//    }
//
//    public static VerifyInfo loadVerifyInfo(String key){
//        logger.debug("{}.info is exist.", key);
//        VerifyInfo verifyInfo = new VerifyInfo();
//        verifyInfo.loadInfo(key);
//        return verifyInfo;
//    }
}
