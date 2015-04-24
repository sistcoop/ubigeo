# ubigeo - restfull services

Es un paquete que permite administrar datos de países segun el estandar ubigeo.

> Revisar ubigeo para mayor informacion.

### Resources
* Departamentos ("/departamentos")

| Method        | URL                                               				| Descripcion           |
| :------------ |:------------------------------------------------------------------|:----------------------|
| GET           | "/departamentos           | Devuelve todos los departamentos      |
| GET           | "/departamentos/{codigo}/provincias"             					| Provincias           	|
| GET           | "/departamentos//{codigoDep}/provincias/{codigoProv}/distritos"	| Distritos				|


Los objetos Departamentos tienen la siguiente estructura:

```json
"departamento": {
    "codigo": "String",
	"denominacion": "String"
}
```

```json
"provincia": {
    "codigo": "String",
	"denominacion": "String",
	"departamento": "String"
}
```

```json
"distrito": {
    "codigo": "String",
	"denominacion": "String",
	"provincia": "String"
}
```

### Version
1.0.0

### Tecnologías

Dependences:

* [JavaEE] - javaEE

Este proyecto es mantenido por SistCoop S.A.C.

License
----

MIT