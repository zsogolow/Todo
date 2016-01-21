package com.baggins.frodo.lib.datasource.dao;

import android.content.Context;

import com.baggins.frodo.lib.datasource.db.LocalDbHelper;
import com.baggins.frodo.lib.datasource.dto.CategoryDTO;
import com.baggins.frodo.lib.datasource.dto.TodoDTO;
import com.baggins.frodo.lib.domain.Category;
import com.baggins.frodo.lib.domain.Todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZachS on 1/10/2016.
 */
public class PortalDAO {

    public static Map<Category, Todo> getTodos(Context context) {
        TodoDAO todoDao = new TodoDAO(context);
        CategoryDAO categoryDAO = new CategoryDAO(context);

        Map<Category, Todo> categoryTodoMap = new HashMap<>();

        List<TodoDTO> todoDTOs = todoDao.get();
        List<CategoryDTO> categoryDTOs = categoryDAO.get();

        for (TodoDTO dto : todoDTOs) {
            Category cat = getCategory(dto.getCategoryId(), categoryDTOs);
            Todo todo = new Todo( dto.getId(), dto.getTodo(), cat, dto.getCreatedAt());
            categoryTodoMap.put(cat, todo);
        }
        return categoryTodoMap;
    }

    private static Category getCategory(int catId, List<CategoryDTO> dtos) {
        for (CategoryDTO dto : dtos) {
            if (dto.getId() == catId) {
                return new Category(dto.getId(), dto.getCategoryName());
            }
        }
        return new Category();
    }

    public static List<Category> getCategories(Context context) {
        CategoryDAO categoryDAO = new CategoryDAO(context);
        List<CategoryDTO> dtos = categoryDAO.get();
        List<Category> categories = new ArrayList<>();
        for (CategoryDTO dto : dtos) {
            Category cat = new Category(dto.getId(), dto.getCategoryName());
            categories.add(cat);
        }
        return categories;
    }

    public static Todo insertTodo(Context context, TodoDTO todoDTO) {
        TodoDAO todoDAO = new TodoDAO(context);
        CategoryDAO categoryDAO = new CategoryDAO(context);
        List<CategoryDTO> dtos = categoryDAO.get();

        TodoDTO inserted = todoDAO.insert(todoDTO);
        return new Todo(inserted.getId(), inserted.getTodo(),
                getCategory(inserted.getCategoryId(), dtos), inserted.getCreatedAt());
    }

    public static Category insertCategory(Context context, CategoryDTO categoryDTO) {
        CategoryDAO categoryDAO = new CategoryDAO(context);

        CategoryDTO inserted = categoryDAO.insert(categoryDTO);
        return new Category(inserted.getId(), inserted.getCategoryName());
    }

    public static Todo insertCategoryAndTodo(Context context, TodoDTO todoDTO, CategoryDTO categoryDTO) {
        CategoryDAO categoryDAO = new CategoryDAO(context);
        TodoDAO todoDAO = new TodoDAO(context);

        CategoryDTO insertedCat = categoryDAO.insert(categoryDTO);
        todoDTO.setCategoryId(insertedCat.getId());

        TodoDTO insertedTodo = todoDAO.insert(todoDTO);
        return new Todo(insertedTodo.getId(), insertedTodo.getTodo(), new Category(insertedCat.getId(),
                insertedCat.getCategoryName()), insertedTodo.getCreatedAt());
    }

    public static void deleteCategory(Context context, CategoryDTO categoryDTO) {
        CategoryDAO categoryDAO = new CategoryDAO(context);
        TodoDAO todoDAO = new TodoDAO(context);

        todoDAO.delete(LocalDbHelper.CATEGORY_ID + " = ?",
                new String[]{String.valueOf(categoryDTO.getId())});

        categoryDAO.delete(LocalDbHelper.CATEGORY_ID + " = ?",
                new String[]{String.valueOf(categoryDTO.getId())});
    }

    public static void deleteTodo(Context context, TodoDTO todoDTO) {
        TodoDAO todoDAO = new TodoDAO(context);

        todoDAO.delete(LocalDbHelper.TODO_ID + " = ?",
                new String[]{String.valueOf(todoDTO.getId())});
    }

    public static TodoDTO updateTodo(Context context, TodoDTO todoDTO) {
        TodoDAO todoDAO = new TodoDAO(context);
        return todoDAO.update(todoDTO);
    }

    public static CategoryDTO updateCategory(Context context, CategoryDTO categoryDTO) {
        CategoryDAO categoryDAO = new CategoryDAO(context);
        return categoryDAO.update(categoryDTO);
    }
}
