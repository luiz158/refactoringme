package academy.devdojo.service;

import academy.devdojo.domain.Language;
import academy.devdojo.dto.ResponseBodyDtoPaginated;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Slf4j
public class LanguageService {

    @Inject
    EntityManager em;
    private static final String SELECT_ALL = "SELECT l FROM Language l";

    public ResponseBodyDtoPaginated<Language> list(int startPage, int pageSize) {
        TypedQuery<Language> languagesQuery = em.createQuery(SELECT_ALL, Language.class);

        languagesQuery.setFirstResult((startPage - 1) * pageSize).setMaxResults(pageSize);

        Long totalResults = (Long) em.createQuery("Select count(l) from Language l").getSingleResult();

        return new ResponseBodyDtoPaginated<>(languagesQuery.getResultList(), totalResults);
    }
}

