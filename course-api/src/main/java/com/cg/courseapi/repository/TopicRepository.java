package com.cg.courseapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.cg.courseapi.domain.Topic;

public interface TopicRepository extends CrudRepository<Topic, String> {

}
