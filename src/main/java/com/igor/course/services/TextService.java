package com.igor.course.services;

import com.igor.course.dao.ExerciseDao;
import com.igor.course.entity.Exercise;
import com.igor.course.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TextService implements ServiceInterface<Exercise> {
    private final ExerciseDao exerciseDao;

    @Autowired
    public TextService(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    @Transactional
    public Exercise get(String id) {
        return exerciseDao.get(id);
    }

    @Override
    @Transactional
    public List<Exercise> getAll() {
        return exerciseDao.getAll();
    }

    @Override
    @Transactional
    public void add(Exercise exercise) {
        exerciseDao.save(exercise);
    }

    @Override
    @Transactional
    public void delete(Exercise exercise) {
        exerciseDao.delete(exercise);
    }

    @Override
    @Transactional
    public void update(String id, Exercise exercise) throws Exception {
        exerciseDao.update(id, exercise);
    }

    @Transactional
    public void addUser(String id, User user) {
        exerciseDao.addUser(id, user);
    }

    @Transactional
    public String getLastId() {
        return exerciseDao.getLastId();
    }
}
