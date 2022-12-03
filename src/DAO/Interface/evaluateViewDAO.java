package DAO.Interface;

import DAO.Entity.evaluateView;

import java.util.List;

public interface evaluateViewDAO {
    List<evaluateView> getEvaluateView(String studentNo);

    List<evaluateView> getAll();
}
