package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import next.model.Answer;
import next.model.Question;
import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementCreator;
import core.jdbc.RowMapper;

public class QuestionDao {
	public List<Question> findAll() {
		String sql = "SELECT questionId, writer, title, createdDate, countOfAnswer FROM QUESTIONS "
				+ "order by questionId desc";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"), null,
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfAnswer"));
			}
			
		};
		
		return JdbcTemplate.get().query(sql, rm);
	}

	public Question findById(long questionId) {
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS "
				+ "WHERE questionId = ?";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"),
						rs.getString("contents"),
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfAnswer"));
			}
		};
		
		return JdbcTemplate.get().queryForObject(sql, rm, questionId);
	}
	
	public Question insert(Question question) {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, question.getWriter());
				pstmt.setString(2, question.getTitle());
				pstmt.setString(3, question.getContents());
				pstmt.setTimestamp(4, new Timestamp(question.getTimeFromCreateDate()));
				pstmt.setInt(5, 0);
				return pstmt;
			}
		};
        
		KeyHolder keyHolder = new KeyHolder();
        JdbcTemplate.get().update(psc, keyHolder);
        return findById(keyHolder.getId());
    }
	
	public Question update(Question question) {
        String sql = "UPDATE QUESTIONS SET writer=?, title=?, contents=? WHERE questionId=?";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, question.getWriter());
				pstmt.setString(2, question.getTitle());
				pstmt.setString(3, question.getContents());
				pstmt.setLong(4, question.getQuestionId());
				return pstmt;
			}
		};
        
		KeyHolder keyHolder = new KeyHolder();
        JdbcTemplate.get().update(psc, keyHolder);
        return findById(keyHolder.getId());
	}

	public boolean isAbleDelete(Question question) {
		if (question.getCountOfComment() == 0)
			return true;
		
		String qWriter = question.getWriter();
		AnswerDao answerDao = new AnswerDao();
		List<Answer> answers = answerDao.findAllByQuestionId(question.getQuestionId());
		for (Answer answer : answers) {
			if (qWriter != answer.getWriter())
				return false;
		}
		
		return true;
	}
	
	public boolean delete(long questionId) {
		Question question = findById(questionId);
		if (!isAbleDelete(question))
			return false;
		
		String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";      
        PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, questionId);
				return pstmt;
			}
		};
        
		KeyHolder keyHolder = new KeyHolder();
        JdbcTemplate.get().update(psc, keyHolder);
        
		return true;
	}
	
	public void addCount(long questionId) {
        String sql = "UPDATE QUESTIONS SET countOfAnswer = countOfAnswer + 1 WHERE questionId = ?";      
        PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, questionId);
				return pstmt;
			}
		};
        
		KeyHolder keyHolder = new KeyHolder();
        JdbcTemplate.get().update(psc, keyHolder);
		
        
	}
	
	public void deleteCount(long questionId) {
        String sql = "UPDATE QUESTIONS SET countOfAnswer = countOfAnswer - 1 WHERE questionId = ?";      
        PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, questionId);
				return pstmt;
			}
		};
        
		KeyHolder keyHolder = new KeyHolder();
        JdbcTemplate.get().update(psc, keyHolder);
	}
	
	public int getCountOfComment(long questionId) {
		Question question = findById(questionId);
	    return question.getCountOfComment();
	}
}
