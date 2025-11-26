package com.reader.adventure.story.edition.dao.h2;

import com.reader.adventure.story.edition.dao.h2.node.NodeH2;
import com.reader.adventure.story.edition.dao.h2.story.StoryH2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StoryDaoH2 {

    @PersistenceContext
    private EntityManager em;

    private Session session() {
        return em.unwrap(Session.class);
    }

    @Transactional
    public void save(StoryH2 story) {
        session().persist(story);
    }

    @Transactional(readOnly = true)
    public StoryH2 find(long id) {
        return session().find(StoryH2.class, id);
    }

    @Transactional(readOnly = true)
    public NodeH2 getNodeById(String id) {
        return session().find(NodeH2.class, id);
    }
}
