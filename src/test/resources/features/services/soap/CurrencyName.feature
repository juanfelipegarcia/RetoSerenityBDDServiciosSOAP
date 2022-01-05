Feature: Nombre de la Divisa
  Yo como Usuario  de un servicio de búsqueda   nombre de la Divisa
  necesito validar que la funcionalidad búsqueda  por código de divisa para el llenado
  de datos que se requieren para validar el nombre de Divisa.

  @searchNombre
  Scenario: búsqueda  nombre de la divisa
    Given que el usuario quiera buscar el codigo ISO "COP"
    When el usuario hace la petición de busqueda la divisa
    Then el ususario debería obtener como ISO "Pesos"

  @searchErrorNombre
  Scenario: búsqueda  errónea nombre de la divisa
    Given que el usuario quiera buscar un indicativo  que no concuerda "cop"
    When el usuario hace la petición de busqueda de la divisa solicitada
    Then el ususario debería obtener como resultado "Currency code NOT found"