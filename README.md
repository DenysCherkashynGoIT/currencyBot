# currencyBot
Телеграмм-бот для отслеживания курса валют в популярных украинских банках. Поддерживает настройки округления, списка валют, автоматические оповещения.
Подключить бота можно по ссылке https://t.me/GoIT_CurrencyBot

Моя работа заключалась в разработке реализации частей Controller и View (полностью пакеты controller/ и view/) MVC-структуры приложения.
Реализованы классы, позволяющие гибко и быстро настраивать меню и подключать новые кнопки и действия по ним, описана реализация команд (паттерн команда)
выполняемых в отдельных потоках для возможности работы с многими пользователями, определены методы приема, обработки и отправки сообщений, 
Application.java реализован main-метод, инициализирующий основные экземпляры класса.

Оcтальные пакеты реализуют бизнес-логику приложения (model-часть) и подготовлены другими учасниками команды.