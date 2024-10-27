package es.israeldelamo.ejemplosqliteandroid


/**
 * como va a ser una clase para guardar datos la precedemos por data,
 * @param id Identificador
 * @param titulo el titulo de la nota
 * @param descripcion la descripcion mas o menos larga de la nota
 */
data class Nota (val id : Int, val titulo : String, val descripcion:String)