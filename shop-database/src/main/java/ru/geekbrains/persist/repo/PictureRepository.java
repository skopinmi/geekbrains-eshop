package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.persist.model.Picture;

import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query("select p from Picture p  where p.id = ?1 and p.pictureData.fileName is not null")
    Optional<Picture> findAllPictureNameById (Long id);

    @Query("select p from Picture p where p.id = ?1 and p.pictureData.data is not null ")
    Optional<Picture> findAllPictureDataById (Long id);
}
