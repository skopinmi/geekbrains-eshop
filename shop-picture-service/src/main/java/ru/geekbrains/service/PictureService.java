package ru.geekbrains.service;

import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.PictureData;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id);

    PictureData createPictureData(byte[] picture);

    // TODO перенести сюда функционал для удаления картинок
    void deleteById(Long id);

    // TODO перенести сюда функционал получения списка картинок
//    @Query("select p from Picture p join PictureData pd where p.id = ?1 and pd.fileName is not null")
//    Optional<Picture> findAllPictureNameById (Long id);

//    @Query("select p from Picture p join PictureData pd where p.id = ?1 and pd.data is not null")
//    List<Picture> findAllPictureDataById (Long id);




}
