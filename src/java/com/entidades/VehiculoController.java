package com.entidades;

import com.entidades.util.JsfUtil;
import com.entidades.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "vehiculoController")
@SessionScoped
public class VehiculoController implements Serializable {

    @EJB
    private com.entidades.VehiculoFacade ejbFacade;
    private List<Vehiculo> items = null;
    private Vehiculo selected;

    public VehiculoController() {
    }
    
    

    public Vehiculo getSelected() {
        return selected;
    }

    public void setSelected(Vehiculo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public VehiculoFacade getFacade() {
        return ejbFacade;
    }

    public Vehiculo prepareCreate() {
        selected = new Vehiculo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VehiculoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VehiculoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VehiculoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
   

    public List<Vehiculo> getItems() {
        
            items = getFacade().findAll();
        
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Vehiculo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Vehiculo> getItemsAvailableSelectOne() {
        List<Vehiculo> lalista=new ArrayList<Vehiculo>();
        List<Vehiculo> natural=getFacade().findAll();
        for (int i = 0; i < natural.size(); i++) { 
            Vehiculo miVehiculo=(Vehiculo) natural.get(i);
                if(miVehiculo.getDisponible().equalsIgnoreCase("si"))
                {
                    lalista.add(miVehiculo);
                }
            }
    return lalista;
    }

    @FacesConverter(forClass = Vehiculo.class)
    public static class VehiculoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VehiculoController controller = (VehiculoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vehiculoController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Vehiculo) {
                Vehiculo o = (Vehiculo) object;
                return getStringKey(o.getPlaca());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Vehiculo.class.getName()});
                return null;
            }
        }

    }

}
