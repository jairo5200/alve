package com.entidades;

import com.entidades.util.JsfUtil;
import com.entidades.util.JsfUtil.PersistAction;

import java.io.Serializable;
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

@ManagedBean(name = "alquilerController")
@SessionScoped
public class AlquilerController implements Serializable {

    @EJB
    private com.entidades.AlquilerFacade ejbFacade;
    private List<Alquiler> items = null;
    private Alquiler selected;

    public AlquilerController() {
    }

    public Alquiler getSelected() {
        return selected;
    }

    public void setSelected(Alquiler selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AlquilerFacade getFacade() {
        return ejbFacade;
    }

    public Alquiler prepareCreate() {
        selected = new Alquiler();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void create1(VehiculoController x) {
        selected.setIdAlquiler(darMayorId());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AlquilerCreated"));
        selected.getVehiculo().setDisponible("no");
        x.setSelected(selected.getVehiculo());
        x.update();
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void create() {
        
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AlquilerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update1(VehiculoController x) {
        selected.getVehiculo().setDisponible("no");
        x.setSelected(selected.getVehiculo());
        x.update();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AlquilerUpdated"));
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AlquilerUpdated"));
    }

    public void destroy1(VehiculoController x) {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AlquilerDeleted"));
        selected.getVehiculo().setDisponible("si");
        x.setSelected(selected.getVehiculo());
        x.update();
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AlquilerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public long darMayorId()
    {
        List<Alquiler> laLista=getItems();
        long mayorId=0;
        for (int i = 0; i < laLista.size(); i++) {
            Alquiler miAlquiler=(Alquiler)laLista.get(i);
            if (miAlquiler.getIdAlquiler()>mayorId) {
                
                mayorId=miAlquiler.getIdAlquiler() ;
            }
        }
        mayorId++;
        return mayorId;
    }

    public List<Alquiler> getItems() {
        
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

    public List<Alquiler> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Alquiler> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Alquiler.class)
    public static class AlquilerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AlquilerController controller = (AlquilerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "alquilerController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Alquiler) {
                Alquiler o = (Alquiler) object;
                return getStringKey(o.getIdAlquiler());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Alquiler.class.getName()});
                return null;
            }
        }

    }

}
