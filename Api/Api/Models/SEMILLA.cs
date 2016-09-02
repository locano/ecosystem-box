//------------------------------------------------------------------------------
// <auto-generated>
//     Este código se generó a partir de una plantilla.
//
//     Los cambios manuales en este archivo pueden causar un comportamiento inesperado de la aplicación.
//     Los cambios manuales en este archivo se sobrescribirán si se regenera el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Api.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class SEMILLA
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public SEMILLA()
        {
            this.INSTRUCCION = new HashSet<INSTRUCCION>();
            this.MACETAS = new HashSet<MACETAS>();
            this.OBTENCION = new HashSet<OBTENCION>();
        }
    
        public int id_semilla { get; set; }
        public string nombre { get; set; }
        public string epoca { get; set; }
        public string tierra { get; set; }
        public int temp_minima { get; set; }
        public int temp_maxima { get; set; }
        public string sol { get; set; }
        public string riego { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<INSTRUCCION> INSTRUCCION { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<MACETAS> MACETAS { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<OBTENCION> OBTENCION { get; set; }
    }
}
