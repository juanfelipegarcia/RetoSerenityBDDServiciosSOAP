Feature: Nombre de la Divisa
  Yo com Usuario  de un servicio de busquda  nombre de la Divisa
  necesito validar que la funcionalidad busqueda por código de divisa para el llenado
  de datos que se requieren para validar el nombre de Divisa.

  @searchISOCode
  Scenario: busqueda  nombre de la divisa
    Given que el usuario quiera buscar el codigo ISO "COP"
    When el usuario hace la petición de busqueda la divisa
    Then el ususario debería obtener como ISO "Pesos"

  @searchNombre
  Scenario: busqueda  erronea nombre de la divisa
    Given que el usuario quiera buscar un indicativo  que no concuerda "cop"
    When el usuario hace la petición de busqueda de la divisa solicitada
    Then el ususario debería obtener como resultado "Currency code NOT found"