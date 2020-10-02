package com.punchthebag.mjtgbot.controller;

import com.punchthebag.mjtgbot.constant.TelegramConstants;
import com.punchthebag.mjtgbot.entity.AnalysisResult;
import com.punchthebag.mjtgbot.request.UpdateRequest;
import com.punchthebag.mjtgbot.service.HandAnalyzerService;
import com.punchthebag.mjtgbot.service.MessageSenderService;
import com.punchthebag.mjtgbot.view.MessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateController {

    private final Logger logger = LoggerFactory.getLogger(UpdateController.class);

    private final HandAnalyzerService handAnalyzerService;
    private final MessageSenderService messageSenderService;
    private final MessageGenerator messageGenerator;

    @Autowired
    public UpdateController(HandAnalyzerService handAnalyzerService,
                            MessageSenderService messageSenderService,
                            MessageGenerator messageGenerator) {
        this.handAnalyzerService = handAnalyzerService;
        this.messageSenderService = messageSenderService;
        this.messageGenerator = messageGenerator;
    }

    @RequestMapping(value = TelegramConstants.WEBHOOK_ADDRESS, method = {RequestMethod.GET, RequestMethod.POST})
    public void update(@RequestBody UpdateRequest updateRequest) {
        logger.info("Starting update: " + updateRequest.toString());
        try {
            AnalysisResult result = handAnalyzerService.analyze(updateRequest.getMessage().getText());

            String response = messageGenerator.generateResponse(result);

            if (response != null) {
                messageSenderService.sendMessage(response, updateRequest.getMessage().getChat().getId());
            }

        } catch (Exception e) {
            logger.warn(e.toString());
        }
        logger.info("Finishing update: " + updateRequest.toString());
    }

}
