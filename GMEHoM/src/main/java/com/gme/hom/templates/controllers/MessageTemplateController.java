package com.gme.hom.templates.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templates/message_template")
public class MessageTemplateController {
	public class Messagefactory {

	}
	/*
	 * private final TemplateService templateService; private final MessagingService
	 * messageService; // private final MessageComposer msgComposer;
	 * 
	 * MessageTemplateController(TemplateService templateService, MessagingService
	 * messageService// ,MessageComposer // msgComposer ) { this.templateService =
	 * templateService; this.messageService = messageService; //
	 * this.msgComposer=msgComposer; }
	 * 
	 * @GetMapping("") public ResponseEntity<APIResponse>
	 * GetMessageTemplates(@RequestBody APIRequest apiRequest, HttpServletRequest
	 * httpRequest, HttpServletResponse httpResponse) { APIResponse ar = new
	 * APIResponse();
	 * 
	 * if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
	 * && apiRequest.getScope().equals(APIRequestScopeCode.ALL.toString())) {
	 * 
	 * List<MessageTemplateDTO> templates = templateService.getAllTemplates();
	 * 
	 * ar.setStatus(APIResponseCode.SUCCESS);
	 * 
	 * ar.setScope(APIResponseScopeCode.MULTIPLE);
	 * 
	 * ar.setDataType("Category");
	 * 
	 * ar.setData(templates);
	 * 
	 * ar.setDescription("Categories list");
	 * 
	 * return ResponseEntity.ok(ar);
	 * 
	 * } else if
	 * (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString()) &&
	 * apiRequest.getScope().equals(APIRequestScopeCode.BYSUBTYPE.toString())) {
	 * 
	 * ar.setDataType("Category");
	 * 
	 * ar.setScope(APIResponseScopeCode.MULTIPLE);
	 * 
	 * APIRequestQueryParams arqs = apiRequest.getData().getQuery();
	 * 
	 * List<MessageTemplateDTO> result =
	 * templateService.getTemplateByTypeAndPurpose(arqs.getForOne(),
	 * arqs.getSubtype());
	 * 
	 * ar.setStatus(APIResponseCode.SUCCESS);
	 * 
	 * ar.setData(result);
	 * 
	 * ar.setDataType("Category");
	 * 
	 * ar.setDescription("Categories list");
	 * 
	 * return ResponseEntity.ok(ar);
	 * 
	 * } else if
	 * (apiRequest.getFunction().equals(APIRequestFunctionCode.SEND_MESSAGE.toString
	 * ()) &&
	 * apiRequest.getScope().equals(APIRequestScopeCode.BYSUBTYPE.toString())) {
	 * 
	 * ar.setDataType("Send Msg");
	 * 
	 * ar.setScope(APIResponseScopeCode.MULTIPLE);
	 * 
	 * APIRequestQueryParams arqs = apiRequest.getData().getQuery();
	 * 
	 * List<MessageTemplateDTO> result =
	 * templateService.getTemplateByTypeAndPurpose(arqs.getForOne(),
	 * arqs.getSubtype());
	 * 
	 * String templateBody = result.get(0).getTemplate(); String templateSubject =
	 * result.get(0).getSubject();
	 * 
	 * Map<String, String> msgData = new HashMap<>(); msgData.put("otp", "11111");
	 * msgData.put("hithere", "how are you");
	 * 
	 * MessageTemplateFactory fac = new MessageTemplateFactory(); //
	 * MessageTemplateBody //
	 * msgBody=fac.getMessageTemplate(MessageTemplateType.SIGNUP_OTP.toString(), //
	 * templateBody, (HashMap<String, String>) msgData);
	 * 
	 * SignupOtpTemplate so = (SignupOtpTemplate)
	 * fac.newMessageTemplate(MessageTemplateTypes.SIGNUP_OTP);
	 * 
	 * so.setOtp("alfsdjldsfj"); so.setTemplate(templateBody);
	 * 
	 * 
	 * 
	 * String messageBody = msgBody.getMessageTemplate(); MessageRequest m = new
	 * MessageRequest(); m.setMessageType("EMAILMIME"); //
	 * m.setContactInfo("surajtest12345@yopmail.com");
	 * m.setContactInfo("surajg@gmeremit.com"); m.setContent(messageBody);
	 * m.setSubject(templateSubject); m.setPurposeType(arqs.getSubtype());
	 * m.setStatus("SENT"); Message mes = new Message(m); //
	 * messageService.sendMessage(mes);
	 * 
	 * ar.setStatus(APIResponseCode.SUCCESS);
	 * 
	 * ar.setData(result);
	 * 
	 * ar.setDataType("Message Template");
	 * 
	 * ar.setDescription("Msg Sent Successfully");
	 * 
	 * return ResponseEntity.ok(ar);
	 * 
	 * } else { ar.setStatus(APIResponseCode.FAILURE); return ResponseEntity.ok(ar);
	 * }
	 * 
	 * }
	 */
}
