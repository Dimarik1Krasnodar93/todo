create table categories_tasks (
id serial primary key,
category_id int not null references categories(id),
task_id int not null references tasks(id)
);