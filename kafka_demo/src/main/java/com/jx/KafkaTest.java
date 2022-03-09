package com.jx;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/kafka")
@Controller
@Slf4j
public class KafkaTest {
    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping(value ="/send")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public void sendMsg(){
        String aa ="{\n" +
                "  \"token\": \"ecd5d1229b3d4206b8071904a2dcaca8\",\t\t\n" +
                "data:[   \n" +
                "{\n" +
                "\"device_id\":\"test1\",\n" +
                "\"gene_id\":\"01\",\n" +
                "collect_data:0.04,\n" +
                "collect_time:\"2019-05-08 01:00:00\"\n" +
                "},\n" +
                "{\n" +
                "\"device_id\":\"test1\",\n" +
                "\"gene_id\":\"02\",\n" +
                "collect_data:0.03,\n" +
                "collect_time:\"2019-05-08 01:00:00\"\n" +
                "},\n" +
                "]\n" +
                "}";
        kafkaProducer.send(KafkaProducer.TOPIC_JX_CNHD_TEST, aa);
    }
}
