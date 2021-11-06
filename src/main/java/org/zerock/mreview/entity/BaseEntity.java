package org.zerock.mreview.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
모든 Entity의 상위 클래스가 되어 regDate와 modDate를 자동으로 관리하는 부모 엔티티를 생성한다.
 */

//객체의 입장에서 공통 매핑 정보가 필요할 때 사용하는 어노테이션
@MappedSuperclass
// @EntityListeners는 엔티티를 DB에 적용하기 이전 이후에 커스텀 콜백을 요청할 수 있는 어노테이션이다.
// value에 설정한 클래스에 구현된 내용이 콜백 옵션에 맞춰 실행된다.
// JPA Auditing을 사용하려면 애플리케이션의 실행포인트에 @EnableJPAAuditing을 적용해 준다.
@EntityListeners(value = {AuditingEntityListener.class})
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "modDate", updatable = false)
    private LocalDateTime modDate;

}
