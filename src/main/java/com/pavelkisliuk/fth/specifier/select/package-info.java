package com.pavelkisliuk.fth.specifier.select;

/**
 * AllAuthenticationEmailSelectSpecifier - возвращает все емэйлы использующиеся для аутентификации
 * AllClientByTrainerSelectSpecifier - возвращает всех клиентов тренера
 * AllRegistrationEmailSelectSpecifier - возвращает все емэйлы пользователей, которые зарегестрировались, но не подтвердили регистрацию
 * AuthenticateClientSelectSpecifier - проверяет есть ли клиент в базе данных (по мэйлу и паролю)
 * !AuthenticateSelectSpecifier - вощврашает количество пользователей с таким емэйлом и паролем (если 1 - пользователь зарегестрирован, если 0 - не зарегистрирован)
 * AuthenticateTrainerSelectSpecifier - проверяет есть ли тренер в базе данных (по мэйлу и паролю).
 * ClientCorrespondWithTrainerSelectSpecifier - проверяет соответствует ли клиент тренеру.
 * ClientGroupByConditionSelectSpecifier - возвращает список клиентов удовлетворяющий определённым условиям.
 * ClientRequestExpiredByIdSelectSpecifier - получаем информацию о клиенте (дата истечения абонемента, оставшееся количество тренировок и есть ли запрос на тренировку)
 * DrillBaseByTrainerSelectSpecifier - Получает все упражнения конкретного тренера
 * <p>
 * <p>
 * EmailExistSelectSpecifier - возвращает количество пользователей с таким емэйлом (из основной таблицы).
 * IdByEmailSpecifier - возвращает ID по емэйлу (тренера и клиента, потом возвращает тот, кторые не равен 0)
 * IsExerciseRequestedSelectSpecifier - возвращает значение запроса пользователем тренировки.
 * LastClientExerciseSelectSpecifier - возвращает ID последней тренировки пользователя.
 * LastNumberClientExerciseSelectSpecifier - возвращает номер последней созданной клиентом тренировки. !!!!исправить!!!!
 * RegistrationEmailExistSelectSpecifier - возвращает количество пользователей с таким емэйлом (из таблицы регистрации).
 * <p>
 * TrainerByIdSpecifier - возвращает основную информацию о тренере по ID
 * TrainerIdByClientSelectSpecifier - возвращает ID тренера по ID клиента
 */
