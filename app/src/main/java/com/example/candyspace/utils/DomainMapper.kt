package com.example.candyspacetest.common

interface DomainMapper<T,DomainModel> {
    fun mapToDomainModel(model:T):DomainModel
    fun mapFromDomainModel(domainModel: DomainModel):T
}