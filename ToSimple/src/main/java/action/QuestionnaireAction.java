package action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import model.Questionnaire;
import model.QuestionnaireResult;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import service.QuestionnaireService;

public class QuestionnaireAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private QuestionnaireService questionnaireService;
	public void setQuestionnaireService(QuestionnaireService questionnaireService) {
		this.questionnaireService = questionnaireService;
	}
	
	
	private String OBJtest;
	private String questionnaireId;
	private String questionnaire;
	private String answerPaper;
	private int status;
	

	


	public String addOrUpdateQuestionnaire() throws Exception{
		questionnaireId=questionnaireService.addOrUpdateQuestionnaire(new Questionnaire(questionnaire));
		
		JSONObject result = new JSONObject();
		result.put("questionnaireId", questionnaireId);
		ServletActionContext.getResponse().getWriter().print(result);
		return null;
	}

	
	public String findAQuestionnaire() throws IOException{
		//questionnaireId="5954b29d37fac38fdc65727c";
		String valid="1";
		String questionnairestr=null;
		if (questionnaireId==null){valid="0";}
		if (questionnaireService.findQuestionnaireById(questionnaireId)==null){
			valid="0";
		}
		else {
			questionnairestr=questionnaireService.findQuestionnaireById(questionnaireId).getQuestionnaire();
		}
		
		JSONObject result = new JSONObject();
		result.put("valid", valid);
		result.put("questionnaire", questionnairestr);
		ServletActionContext.getResponse().getWriter().print(result);
		return null;
	}
	
	public String setQuestionnaireStatus() throws IOException{
		String valid="1";
		if (questionnaireId==null){valid="0";}
		Questionnaire questionnaireS=questionnaireService.findQuestionnaireById(questionnaireId);
		if (questionnaireS==null){
			valid="0";
		}
		else {
			questionnaireS.setStatus(status);
			questionnaireService.addOrUpdateQuestionnaire(questionnaireS);
		}
		ServletActionContext.getResponse().getWriter().print(valid);
		return null;
	}
	
	public String addQuestionnaireResult() throws IOException{
		if (answerPaper==null){//data not fetched, fail
			ServletActionContext.getResponse().getWriter().print('0');
			return null;
		}
		questionnaireService.addQuestionnaireResult(new QuestionnaireResult(answerPaper, request()));
		ServletActionContext.getResponse().getWriter().print('1');//success
		return null;
	}

	
	
	
	
	//helper
	public String getAnswerPaper() {
		return answerPaper;
	}

	public void setAnswerPaper(String answerPaper) {
		this.answerPaper = answerPaper;
	}

	public String getOBJtest() {
		return OBJtest;
	}

	public void setOBJtest(String oBJtest) {
		OBJtest = oBJtest;
	}

	public String getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(String questionnaire) {
		this.questionnaire = questionnaire;
	}
	
	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}

	

}
