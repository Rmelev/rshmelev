# Содержание курса по главам:

chapter_001 (Basic Syntax): 
1. Интеграция в курс инструментов и окружения (Maven, JUnit, Git, JavaDoc, CheckStyle, Intellij IDEA, TrackStudio).
2. Практические задачи на типы данных, циклы, условия, массивы, строки.
3. Реализация сортировок, конвертера, калькулятора.
4. Отладка в режиме Debug.

chapter_002 (OOP):
1. Классы и объекты, наследование, инкапсуляция, полиморфизм.
2. Внутренние, абстрактные и анонимные классы.
3. Исключения.
4. Реализация трекера заявок на базе массива.
5. Тестирование трекера, использование заглушек.
6. Шаблоны проектирования: Adapter, Strategy.
7. Основы рефакторинга.
8. Реализация псевдо шахмат.

chapter_003 (Collections Lite):
1. List, Set, Map, Comparator, Comparator.
2. Generic.
3. Реализация трекера заявок на коллекциях.
4. Сортировки.
5. JaCoCo, Travis CI, peer review.
6. Эмуляция банковских переводов между клиентами.

chapter_005 (Collections Pro):
1. Более глубокое рассмотрение List, Set, Map, Tree, Iterator.
2. Более глубокое рассмотрение Generic.
3. Создание собственных реализация итераторов (четные, простые числа, итератор итераторов).
4. Создание хранилищ сущностей с использованием Generic.
5. Создание собственных реализаций ArrayList, LinkeList, Set, Tree, HashMap, Stack, Queue.
6. Override equals(), hashCode().
7. Реализация binary search tree.
8. Реализация биржевого стакана (данные поступают в XML-файле).

chapter_007 (Multithreading):
1. JMM, race condition.
2. Thread States, Monitor, Synchronization, jcip-annotations.
3. wait(), notify(), notifyAll().
4. Non Blocking Algorithms.
5. Thread-safe collections.
6. Реализация потокобезопасности коллекций из chapter_005 пункт 5.
7. Реализация ThreadPool, интерфейсы Future, Callable.
8. Реализация собственного механизма блокировок (аналогично интерфейсу Lock).
9. Реализация шаблона Producer Consumer.
10. Симулятор игры Bomberman.

chapter_008 (SQL, JDBC):
1. UML диаграммы.
2. Queries.
3. Inner join, outer join.
4. Реализация хранилища автомобилей, каждый из которых состоин из сущностей с определенными характиестиками.
5. Parsing with JAXB.
6. Реализация: запись данных в БД (SQLite), запрос из БД и запись в XML, преобразование XML через XSLT.
7. Реализация трекера заявок с хранением данных в БД (Postgres).

chapter_009 (Servlets, JSP):
1. Структура клиент-серверных приложений, HTTP-протокол.
2. Создание CRUD web-проекта на базе Apache Tomcat.
3. Test RESTFull service
4. Pattern Singleton, eager initialization, Thread Safe Singlton, load-on-startup.
5. JSP, JSTL.
6. MVC.
7. Filter, Security.
8. Mockito.
9. HTML, CSS, JS, ajax.
10. Реализация web-проекта с использовнием изученных технологий и языков программирования.
11. Создание Music Store c взаимосвязанными сущностями (User, Address, Role, MusicType).
12. DAO pattern, Repository pattern.


chapter_010_hibernate (Hibernate):
1. Конфигурирование.
2. Создание web-проекта TODO list.
3. Mapping.
4. Реализация хранилища автомобилей (chapter_008 пункт 4)посредством Hibernate.
5. Реализация web-проекта "Площадка продажи автомобилей", apache fileuppload API.
6. Реализация фильтров в "Площадке продажи автомобилей".
7. Интреграционное тестирование web-проектов.

chapter_011_spring (Spring):
1. 

chapter_033 (вынесено отдельно задание chapter_010 пункт 2):
1. Создание web-проекта TODO list.

music_store (вынесено отдельно задание chapter_009 пункт 11):
1. Создание Music Store c взаимосвязанными сущностями (User, Address, Role, MusicType)

