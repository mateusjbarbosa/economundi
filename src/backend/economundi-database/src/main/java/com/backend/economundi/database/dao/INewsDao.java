package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.News;
import java.util.List;

public interface INewsDao {
    
    public static String ID = "id";
    public static String TITLE = "manchete";
    public static String DESCRIPTION = "descricao";
    public static String CONTENT = "conteudo";
    public static String SOURCE = "fonte";
    public static String URL = "link";
    public static String URL_IMAGE = "link_imagem";
    public static String LOCALITY = "localidade";
    public static String RELEVANCE = "engajamento";
    public static String ENTITY = "noticia";
    public static String DATE = "data_hora";
    
    public void create(News news);
    
    public void update(News news);
    
    public List<News> readNewsWithRelevance();
}
