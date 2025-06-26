package com.example.music_app.repository;

import com.example.music_app.model.Song;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SongRepository implements ISongRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Song> findAll() {
        TypedQuery<Song> songTypedQuery = entityManager.createQuery("FROM Song ", Song.class);
        return songTypedQuery.getResultList();
    }

    @Override
    public Song findById(Long id) {
        return entityManager.find(Song.class, id);
    }

    @Override
    public void save(Song song) {
        if (song.getId() == null) {
            entityManager.persist(song);
        } else {
            entityManager.merge(song);
        }
    }

    @Override
    public void delete(Long id) {
        Song song = findById(id);
        if (song != null) {
            entityManager.remove(song);
        }
    }
}
