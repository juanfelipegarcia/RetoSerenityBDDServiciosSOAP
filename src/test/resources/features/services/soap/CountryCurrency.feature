Feature: ISO code y nombre de la Divisa
  Yo com Usuario  de un servicio de busquda de ISO code y nombre de la moneda
  necesito validar que la funcionalidad busqueda por código de pais para el llenado
  de datos que se requieren para validar el tipo de Divisa.

  @searchISOCode
  Scenario: busqueda ISO code de la vivisa
    Given que el usuario quiera buscar el codigo "CO"
    When el usuario hace la petición de busqueda la divisa requerida
    Then el ususario debería obtener como iso divisa "COP"

  @searchNombre
  Scenario: busqueda  nombre de la divisa
    Given que el usuario quiera buscar un indicativo  que no coincidan "CO"
    When el usuario hace la petición de busqueda de la divisa
    Then el ususario debería obtener como resultado el valor "Pesos"