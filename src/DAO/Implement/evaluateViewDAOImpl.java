package DAO.Implement;
import DAO.Entity.Course;
import DAO.Entity.Subject;
import DAO.Entity.evaluateView;
import DAO.Entity.subjectHeaderView;
import DAO.Interface.evaluateViewDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class evaluateViewDAOImpl extends DAObase implements evaluateViewDAO{
    private final String SELECT_SQL="select studentName,studentNo,courseName,teachingNumber,subjectName,properties,type,teacherName,teachingTime,jobReadme,teacherEvaluation,studentSign,studentSignTime,teacherSign,teacherSignTime  from evaluateView where studentNo=?";

    private final String SELECT_ALL="select studentName,studentNo,courseName,teachingNumber,subjectName,properties,type,teacherName,teachingTime,jobReadme,teacherEvaluation,studentSign,studentSignTime,teacherSign,teacherSignTime  from evaluateView";
    @Override

    public List<evaluateView> getEvaluateView(String studentNo) {
        List<evaluateView> list = new ArrayList<>();
        try{
            PreparedStatement p=getConnection().prepareStatement(SELECT_SQL);
            p.setString(1,studentNo);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                evaluateView evaluateView=new evaluateView();

                evaluateView.setStudentName(resultSet.getString("studentName"));
                evaluateView.setStudentNo(resultSet.getString("studentNo"));
                evaluateView.setCourseName(resultSet.getString("courseName"));
                evaluateView.setTeachingNumber(resultSet.getString("teachingNumber"));
                evaluateView.setSubjectName(resultSet.getString("subjectName"));
                evaluateView.setProperties(resultSet.getString("properties"));
                evaluateView.setType(resultSet.getString("type"));
                evaluateView.setTeacherName(resultSet.getString("teacherName"));
                evaluateView.setTeachingTime(resultSet.getString("teachingTime"));
                evaluateView.setJobReadme(resultSet.getString("jobReadme"));
                evaluateView.setTeacherEvaluation(resultSet.getString("teacherEvaluation"));
                evaluateView.setStudentSign(resultSet.getString("studentSign"));
                evaluateView.setStudentSignTime(resultSet.getString("studentSignTime"));
                evaluateView.setTeacherSign(resultSet.getString("teacherSign"));
                evaluateView.setTeacherSignTime(resultSet.getString("teacherSignTime"));

                list.add(evaluateView);
            }
            p.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                getConnection().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<evaluateView> getAll() {
        List<evaluateView> list = new ArrayList<>();
        try{
            PreparedStatement p=getConnection().prepareStatement(SELECT_ALL);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){

                if(resultSet.getString("studentSign")==null){
                    break;
                }else if(resultSet.getString("studentSignTime")==null){
                    break;
                } else if (resultSet.getString("teacherSign")==null) {
                    break;
                } else if (resultSet.getString("teacherSignTime")==null) {
                    break;
                }

                evaluateView evaluateView=new evaluateView();
                evaluateView.setStudentName(resultSet.getString("studentName"));
                evaluateView.setStudentNo(resultSet.getString("studentNo"));
                evaluateView.setCourseName(resultSet.getString("courseName"));
                evaluateView.setTeachingNumber(resultSet.getString("teachingNumber"));
                evaluateView.setSubjectName(resultSet.getString("subjectName"));
                evaluateView.setProperties(resultSet.getString("properties"));
                evaluateView.setType(resultSet.getString("type"));
                evaluateView.setTeacherName(resultSet.getString("teacherName"));
                evaluateView.setTeachingTime(resultSet.getString("teachingTime"));
                evaluateView.setJobReadme(resultSet.getString("jobReadme"));
                evaluateView.setTeacherEvaluation(resultSet.getString("teacherEvaluation"));
                evaluateView.setStudentSign(resultSet.getString("studentSign"));
                evaluateView.setStudentSignTime(resultSet.getString("studentSignTime"));
                evaluateView.setTeacherSign(resultSet.getString("teacherSign"));
                evaluateView.setTeacherSignTime(resultSet.getString("teacherSignTime"));

                list.add(evaluateView);
            }
            p.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                getConnection().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }
}
