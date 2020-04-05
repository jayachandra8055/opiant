package com.oa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oa.model.MathExpression;

@Repository
public interface ExpressionRepository extends JpaRepository<MathExpression, Long> {

}
