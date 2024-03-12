package gabriel.testebackend.todolist.Dtos;

import gabriel.testebackend.todolist.enities.PriorityEnum;

public record ActivityDto(String name, String description, Boolean done, PriorityEnum priorityEnum) {
}
