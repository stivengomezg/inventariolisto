package model;


import model.bussinesException.BussinesException;
import model.util.Validator;

public class Inventario {
    public static final String CANTIDAD_INVENTARIO_ES_MAYOR_AL_EXISTENTE ="La cantidad a retirar es mayor a la cantidad del" +
            " producto disponible" ;
    public static final String PRODUCTO_REQUERIDO="El producto es requerido";
    public static final String CANTIDAD_REQUERIDA_PRODUCTO="La cantidad de producto es requerida";
    public static final String CANTIDAD_REQUERIDA_INVENTARIO="La cantidad de inventario es requerida";
    public static final String CANTIDAD_REQUERIDA_LIMITE_INFERIOR="La cantidad de limite inferior es requerida";
    public static final String CANTIDAD_REQUERIDA_LIMITE_SUPERIOR="La cantidad de limite superior es requerida";
    public static final String CANTIDAD_NEGATIVA_INVENTARIO ="La cantidad de inventario no puede ser  menor que cero." ;
    public static final String CANTIDAD_NEGATIVA_PRODUCTO ="La cantidad de producto no puede ser  menor que cero." ;
    public static final String CANTIDAD_NEGATIVA_LIMITE_SUPERIOR ="El limite superior no puede ser  menor que cero." ;
    public static final String CANTIDAD_NEGATIVA_LIMITE_INFERIOR ="El limite inferior no puede ser  menor que cero." ;
    public static final String LIMITE_INFERIOR_ALCANZADO = "La cantidad del producto es menor a 5";
    public static final String LIMITE_SUPERIOR_ALCANZADO = "La cantidad del producto ha sobrepasado el tope";
    private int idInventario;
    private int cantidadInventario;
    private Producto producto;
    private int limiteInferio;
    private int limiteSuperior;


    private Inventario() {
        super();
    }



    public static class inventarioBuilder {

        private int idInventario;
        private Producto producto;
        private int cantidadInventario;
        private int limiteInferio;
        private int limiteSuperior;

        public inventarioBuilder setLimiteInferio(int limiteInferio) {
            this.limiteInferio = limiteInferio;
            return this;
        }

        public inventarioBuilder setLimiteSuperior(int limiteSuperior) {
            this.limiteSuperior = limiteSuperior;
            return this;
        }


        public inventarioBuilder setCantidadInventario(int cantidadInventario) {
            this.cantidadInventario = cantidadInventario;
            return this;
        }


        public inventarioBuilder setIdInventario(int idInventario) {
            this.idInventario = idInventario;
            return this;
        }

        public inventarioBuilder setProducto(Producto producto) {
            this.producto = producto;
            return this;
        }

        public Inventario build() throws BussinesException {
            Inventario inventario=new Inventario();
            inventario.idInventario=this.idInventario;
            Validator.validarCantidadNegativa(producto.getCantidad(),CANTIDAD_NEGATIVA_PRODUCTO);
            Validator.validarProductoNulo(producto,PRODUCTO_REQUERIDO);
            Validator.validarCantidadVacia(producto.getCantidad(),CANTIDAD_REQUERIDA_PRODUCTO);
            inventario.producto=this.producto;
            Validator.validarCantidadNegativa(cantidadInventario,CANTIDAD_NEGATIVA_INVENTARIO);
            Validator.validarCantidadVacia(cantidadInventario, CANTIDAD_REQUERIDA_INVENTARIO);
            Validator.validarInventarioTransaccion(producto.getCantidad(),cantidadInventario,CANTIDAD_INVENTARIO_ES_MAYOR_AL_EXISTENTE);
            inventario.cantidadInventario=this.cantidadInventario;
            Validator.validarCantidadNegativa(limiteInferio,CANTIDAD_NEGATIVA_LIMITE_INFERIOR);
            Validator.validarCantidadVacia(limiteInferio, CANTIDAD_REQUERIDA_LIMITE_INFERIOR);
            Validator.validarLimiteInferior(producto.getCantidad(),limiteInferio, LIMITE_INFERIOR_ALCANZADO);
            inventario.limiteInferio=this.limiteInferio;
            Validator.validarCantidadNegativa(limiteSuperior,CANTIDAD_NEGATIVA_LIMITE_SUPERIOR);
            Validator.validarCantidadVacia(limiteSuperior, CANTIDAD_REQUERIDA_LIMITE_SUPERIOR);
            Validator.validarLimiteSuperior(producto.getCantidad(),limiteSuperior,  LIMITE_SUPERIOR_ALCANZADO);
            inventario.limiteSuperior=this.limiteSuperior;
            return inventario;

        }


    }
    public int retirarProducto(){



            return ((producto.getCantidad() - cantidadInventario));

    }
    public int ingresarProducto(){

            return ((producto.getCantidad() + cantidadInventario));

    }





}


