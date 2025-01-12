CREATE TABLE if not exists education (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200),
    user_id UUID NOT NULL,
    total INT DEFAULT 0,
    done_edu INT DEFAULT 0,
    created_at TIMESTAMP,
    start_at TIMESTAMP,
    end_at TIMESTAMP,
    status VARCHAR(50)
);

CREATE INDEX if not exists idx_hash_user_id ON education USING hash (user_id);

-- Добавление столбца 'total' если он не существует
ALTER TABLE education
    ADD COLUMN IF NOT EXISTS total INT DEFAULT 0;

-- Добавление столбца 'done_edu' если он не существует
ALTER TABLE education
    ADD COLUMN IF NOT EXISTS done_edu INT DEFAULT 0;

CREATE TABLE if not exists training_block (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    num INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    edu_id UUID REFERENCES education(id) ON DELETE CASCADE,
    start_at TIMESTAMP,
    end_at TIMESTAMP,
    status VARCHAR(50),
    CONSTRAINT block_num_unique UNIQUE(edu_id, num)
);


CREATE TABLE if not exists lesson (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    num INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    block_id UUID REFERENCES training_block(id) ON DELETE CASCADE,
    start_at TIMESTAMP,
    end_at TIMESTAMP,
    status VARCHAR(50),
    CONSTRAINT lesson_num_unique UNIQUE(block_id, num)
);
