#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* ServletContextListener 들을 초기화한다. 그 중 ContextLoaderListener 는 jwp.sql 파일에 기록되어 있는 SQL 명령어를 순차적으로 실행하여 DB 를 초기화한다.
* DispatcherServlet 이 생성되면서 서버에서 처리할 URL 목록과 처리될 Controller 를 Mapping 하여 저장해 둔다. (RequestMapping 의 initMapping() 함수)

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 서버에 해당 요청이 들어오면 ResourceFilter 를 통해 해당 URL 이 단순 Resource 요청(css, js, fonts, image, favicon.ico)인지를 확인한다. 단순 Resource 요청인 경우 요청 URL 로 바로 Forward 를 시켜준다. 현재 http://localhost:8080 요청은 단순 Resource 요청이 아니기 때문에 DispatcherServlet 의 service() 함수에서 처리된다. 일단, RequestMapping 을 통해 요청 URL 인 ‘/‘ 에 대한 Mapping controller 인 HomeController 를 찾아낸다. 이후 HomeController 의 execute() 함수가 실행되면서 ‘index.jsp’ 파일로 JspView 인스턴스를 생성하고, 해당 instance 에 SQL 에 저장되어 있는 모든 Questions 을 찾아 추가한다. 마지막으로 JspView 인스턴스가 ‘index.jsp’ 로 Forward 하면, 서블릿 컨테이너에서 서블릿 원시코드로 변환 후 컴파일된 후 HTML 형태로 클라이언트에 돌려준다.

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 모르겠습니다..