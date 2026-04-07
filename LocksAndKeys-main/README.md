# Locks And Keys
## Aquest projecte ha estat realitzat per Lidia Gil Martín i Pablo Martín Martín

Per aquest projecte, hem afegit classes addicionals per facilitar el desenvolupament i minimitzar les modificacions a les classes inicials proporcionades. A més, hem incorporat funcions públiques a la classe Mapa per simplificar l’accés a certes dades que necessitàvem com obtenir la cuadricula per precalcular dades que ens han facilitat trobar la solució de manera més eficient.

La classe NodeExtended, com indica el seu nom, extén la classe Node, afegint un nou atribut costTotal, que ens permet emmagatzemar la suma del cost del node i la seva heurística.

La classe CercaDopada proporciona nous mètodes que eviten la repetició de codi i la reconstrucció del camí en les cerques, i aquests mètodes seran reutilitzats per les classes que en derivin. Així, la jerarquia de la classe Cerca queda de la següent manera:

Cerca → CercaDopada → CercaBFS/CercaDFS…
