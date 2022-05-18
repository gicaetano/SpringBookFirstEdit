package com.book.todo.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.book.todo.entity.TaskEntity;

@RepositoryRestResource(path="task", collectionResourceRel = "tasks")
public interface TaskRepository extends JpaRepository<TaskEntity, Integer>,
JpaSpecificationExecutor<TaskEntity>, QuerydslPredicateExecutor<TaskEntity> {
	
	Page<TaskEntity> findByIdIn(@Param(value = "id")List<Integer> eventid, Pageable pageable);
	
	Page<TaskEntity> findByNameIn(@Param("name")Collection<String>names,Pageable pageable);
	
	@Query(name="Task.findByName", nativeQuery = true)
	List<TaskEntity> findByName(@Param("name")String name);
	
	Page<TaskEntity> findAll(Pageable pageable);
	
	@Query(name="Task.findById", nativeQuery=true)
	@RestResource(exported=false)
	Optional<TaskEntity>findById(@Param("id")long id);

}
