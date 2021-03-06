package com.example.web123.repository;
import com.example.web123.entity.Notepad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

public interface NotepadRepository extends JpaRepository<Notepad, UUID> {

    List<Notepad> findByUserID(UUID uuid);

    @Modifying
    @Query(value ="update Notepad  set content=:content  where id=:id")
    @Transactional
    void updateNotepad(@Param("content") String content, @Param("id") UUID id);

    @Query(value="select * from notepad where title=:title AND userid=:userid",nativeQuery = true)
    @Transactional
    List<Notepad> searchNotepad(@Param("title") String title,@Param("userid") UUID id);

}
