using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using Api.Models;
using System.Runtime.Serialization.Json;
using System.Web;
namespace Api.Controllers
{
    public class SEMILLAController : ApiController
    {
        private dbb_GudBoxEntities db = new dbb_GudBoxEntities();

        // GET: api/SEMILLA
        public IHttpActionResult GetSEMILLA()
        {
            return Json(new { nombre = "hola" });
        }

        // GET: api/SEMILLA/5
        [ResponseType(typeof(SEMILLA))]
        public IHttpActionResult GetSEMILLA(int id)
        {
            SEMILLA sEMILLA = db.SEMILLA.Find(id);
            if (sEMILLA == null)
            {
                return NotFound();
            }

            return Ok(sEMILLA);
        }

        // PUT: api/SEMILLA/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutSEMILLA(int id, SEMILLA sEMILLA)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != sEMILLA.id_semilla)
            {
                return BadRequest();
            }

            db.Entry(sEMILLA).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SEMILLAExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/SEMILLA
        [ResponseType(typeof(SEMILLA))]
        public IHttpActionResult PostSEMILLA(SEMILLA sEMILLA)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.SEMILLA.Add(sEMILLA);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (SEMILLAExists(sEMILLA.id_semilla))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = sEMILLA.id_semilla }, sEMILLA);
        }

        // DELETE: api/SEMILLA/5
        [ResponseType(typeof(SEMILLA))]
        public IHttpActionResult DeleteSEMILLA(int id)
        {
            SEMILLA sEMILLA = db.SEMILLA.Find(id);
            if (sEMILLA == null)
            {
                return NotFound();
            }

            db.SEMILLA.Remove(sEMILLA);
            db.SaveChanges();

            return Ok(sEMILLA);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool SEMILLAExists(int id)
        {
            return db.SEMILLA.Count(e => e.id_semilla == id) > 0;
        }
    }
}