package com.example.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemo is a Querydsl query type for Memo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemo extends EntityPathBase<Memo> {

    private static final long serialVersionUID = -1524178996L;

    public static final QMemo memo = new QMemo("memo");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isPublic = createBoolean("isPublic");

    public final StringPath memberEmail = createString("memberEmail");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath title = createString("title");

    public QMemo(String variable) {
        super(Memo.class, forVariable(variable));
    }

    public QMemo(Path<? extends Memo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemo(PathMetadata metadata) {
        super(Memo.class, metadata);
    }

}

