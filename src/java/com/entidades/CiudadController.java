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

@ManagedBean(name = "ciudadController")
@SessionScoped
public class CiudadController implements Serializable {

    @EJB
    private com.entidades.CiudadFacade ejbFacade;
    private List<Ciudad> items = null;
    private Ciudad selected;

    public CiudadController() {
    }

    public Ciudad getSelected() {
        return selected;
    }

    public void setSelected(Ciudad selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CiudadFacade getFacade() {
        return ejbFacade;
    }

    public Ciudad prepareCreate() {
        selected = new Ciudad();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setIdCiudad(darMayorId());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CiudadCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CiudadUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CiudadDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    
    public long darMayorId()
    {
        List<Ciudad> laLista=getItems();
        long mayorId=0;
        for (int i = 0; i < laLista.size(); i++) {
            Ciudad miCiudad=(Ciudad)laLista.get(i);
            if (miCiudad.getIdCiudad()>mayorId) {
                
                mayorId=miCiudad.getIdCiudad() ;
            }
        }
        mayorId++;
        return mayorId;
    }

    public List<Ciudad> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
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

    public List<Ciudad> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Ciudad> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Ciudad.class)
    public static class CiudadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CiudadController controller = (CiudadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ciudadController");
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
            if (object instanceof Ciudad) {
                Ciudad o = (Ciudad) object;
                return getStringKey(o.getIdCiudad());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ciudad.class.getName()});
                return null;
            }
        }

    }

}
