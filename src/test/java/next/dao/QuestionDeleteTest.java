package next.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;
import next.model.Answer;

//13.질문 삭제 기능을 구현한다. 질문 삭제가 가능한 경우는 다음과 같다. “답변이 없는 경우 삭제가 가능하다. 질문자와 답변자가 같은 경우 삭제가 가능하다. 
//질문자와 답변자가 다른 경우 답 변을 삭제할 수 없다”. 이 질문 삭제 기능은 일반 PC와 모바일 모두를 지원하려고 한다. 
//삭제 를 완료한 후 일반 PC의 웹 브라우저는 JspView를 활용해 목록 페이지(“redirect:/”)로 이동하 고, 모바일은 JsonView를 활용해 응답 결과를 JSON으로 전송하려고 한다. 
//이를 지원하려면 DeleteQuestionController, ApiDeleteQuestioncontroller 이름을 가지는 2개의 Controller를 구현해야 한다. 두 개의 Controller를 구현해보면 많은 중복이 발생한다. 각 Controller에서 발 생하는 중복을 제거한다. 상속 또는 새로운 클래스를 만들어 위임할 수 있다(composition, 조 합이라고 한다.). 어느 방식으로 중복을 제거하는 것이 좋을지에 대해서도 고민해 본다.
//14.(선택)13번 문제를 구현할 때 단위 테스트가 가능하도록 구현한다. Dao를 사용하는 모든 Controller 클래스는 데이터베이스가 설치되어 있어야 하며, 
//테이블까지 생성되어 있는 상태에 서만 테스트가 가능하다. 데이터베이스가 존재하지 않는 상태에서도 위 로직을 단위 테스트하고 싶다.
//(힌트 : Dependency Injection, Map을 활용한 메모리 DB, Mockito 테스트 프레임워크)

public class QuestionDeleteTest {
	@Before
    public void setup() {
        QuestionDao qDao = mock(QuestionDao.class);
    }
    
    @Test
    public void deleteNoAnswer() throws Exception {
    	boolean result = true;
    	
    	assertEquals(result, false);
    }
    
    @Test
    public void deleteDiffWriter() throws Exception {
    	boolean result = true;
    	
    	assertEquals(result, false);
    }
    
    @Test
    public void deleteSameWriter() throws Exception {
    	boolean result = false;
    	
    	assertEquals(result, true);
    }
}
