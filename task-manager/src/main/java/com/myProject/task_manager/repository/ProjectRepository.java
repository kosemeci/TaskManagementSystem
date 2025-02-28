package com.myProject.task_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myProject.task_manager.entity.Project;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Integer>{

    @Query(value = "SELECT COUNT(*) FROM task WHERE project_id = :projectId ",nativeQuery = true)
    int countTotalTask(@Param ("projectId") Integer projectId);

    @Query(value = "SELECT COUNT(*) FROM task WHERE project_id = :projectId AND status='COMPLETED'",nativeQuery = true)
    int countTaskCompleted(@Param ("projectId") Integer projectId);

    @Query(value = "SELECT COUNT(*) FROM task WHERE project_id = :projectId AND status='PENDING'",nativeQuery = true)
    int countTaskPending(@Param ("projectId") Integer projectId);

    @Query(value = "SELECT COUNT(*) FROM task WHERE project_id = :projectId AND status='IN_PROGRESS'",nativeQuery = true)
    int countTaskInProgress(@Param ("projectId") Integer projectId);

    @Query(value = "SELECT COUNT(*) FROM task WHERE project_id = :projectId AND status='FAILED'",nativeQuery = true)
    int countTaskFailed(@Param ("projectId") Integer projectId);

    @Query(value = "SELECT COUNT(*) FROM task WHERE project_id = :projectId AND status='CANCELLED'",nativeQuery = true)
    int countTaskCancelled(@Param ("projectId") Integer projectId);

    @Query(value = "SELECT COUNT(DISTINCT user_id) FROM tasks WHERE project_id = :projectId", nativeQuery = true)
    int countUniqueUsers(@Param("projectId") Integer projectId);
}
