package com.backend.economundi.service;

import com.backend.economundi.database.dao.IWordDao;
import com.backend.economundi.database.dao.entity.WordEntity;
import com.backend.economundi.database.dao.impl.WordAccessDao;
import com.backend.economundi.database.dao.impl.WordDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class WordService {

    @Autowired
    private final IWordDao wordDao = new WordDao();

    /**
     *
     * Adiciona aos controles do dicionário.
     *
     * @param word Nova palavra a ser criada.
     * @return Houve ou não sucesso na criação.
     */
    public Map<String, String> create(WordEntity word) {
        Map<String, String> errors = validate(word);

        if (errors.isEmpty()) {
            word.setName(word.getName().toUpperCase().trim());
            word.setDescription(word.getDescription().trim());

            wordDao.create(word);
        }

        return errors;
    }

    /**
     * Pesquisa de palavras por id.
     *
     * @param id Identificador da palavra a ser encontrada.
     * @return Palavra caso exista.
     */
    public WordEntity readById(Long id) {
        WordEntity word = wordDao.read(id);

        if (word != null) {
            WordAccessDao wordAccessDao = new WordAccessDao();
            wordAccessDao.create(id);
        }

        return word;
    }

    /**
     * Realiza pesquisa por substring.
     *
     * @param substring A string que se deseja encontrar.
     * @return Id e nome da palabra que contém a substring.
     */
    public Map<Long, String> readBySubString(String substring) {
        List<WordEntity> wordList = wordDao.readByName(substring);
        Map<Long, String> wordMap = new HashMap<>();

        wordList.forEach((word) -> {
            wordMap.put(word.getId(), word.getName());
        });

        return wordMap;
    }

    /**
     * Atualiza a palavra.
     *
     * @param body Palavra com suas modificações.
     * @return Motivo de erro, caso exista.
     */
    public Map<String, String> update(Map<String, String> body) {
        WordEntity word = merge(body);
        Map<String, String> errors = validate(word);

        if (errors.isEmpty()) {
            wordDao.update(word);
        }

        return errors;
    }

    /**
     * Deleta a palavra.
     *
     * @param id Identificador da palavra que se deseja deletar.
     */
    public void delete(Long id) {
        WordEntity word = wordDao.read(id);

        if (word != null) {
            wordDao.delete(word);
        }
    }

    /**
     * Realiza o merge apenas dos valores que estiver mapeado.
     *
     * @param data Mapeamento com um conjunto de chave e valor.
     * @return Palavra editada, se houver.
     */
    private WordEntity merge(Map<String, String> data) {
        WordEntity merged = null;

        if (data != null) {
            Long id = Long.parseLong(data.get("id"));

            if (id != 0) {
                WordEntity word = wordDao.read(id);

                if (word != null) {
                    String key = "name";

                    merged = new WordEntity();

                    merged.setId(word.getId());
                    merged.setName(word.getName());
                    merged.setDescription(word.getDescription());

                    if (data.containsKey(key)) {
                        merged.setName(data.get(key).toUpperCase().trim());
                    }

                    key = "description";

                    if (data.containsKey(key)) {
                        merged.setDescription(data.get(key).trim());
                    }
                }
            }
        }

        return merged;
    }

    /**
     * Valida se os campos obrigatórios realmente estão preenchidos.
     *
     * @param word Palavra a ser validada.
     * @return Mapeamento com os erros, se houver.
     */
    private Map<String, String> validate(WordEntity word) {
        Map<String, String> errors = new HashMap<>();

        if (word != null) {
            String name = word.getName();

            if (name == null || name.isEmpty()) {
                errors.put("Nome", "A palavra precisa de um nome.");
            }

            String description = word.getDescription();

            if (description == null || description.isEmpty()) {
                errors.put("Descrição", "A palavra precisa de uma definição");
            }
        } else {
            errors.put("Entidade", "Não há palavra.");
        }

        return errors;
    }

    /**
     * Informa as palavras mais pesquisadas no sistema.
     *
     * @return Mapa com id e palavra (correspondente) mais pesquisadas.
     */
    public Map<Long, String> getTopSearch() {
        WordAccessDao wordAccessDao = new WordAccessDao();
        Map<Long, String> mapTop = wordAccessDao.getMostSearched();

        return mapTop;
    }
}
