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

namespace Api.Controllers
{
    public class COLLECTIONController : ApiController
    {
        private dbb_GudBoxEntities db = new dbb_GudBoxEntities();

        // GET: api/COLLECTION
        public IQueryable<COLLECTION> GetCOLLECTION()
        {
            return db.COLLECTION;
        }

        // GET: api/COLLECTION/5
        [ResponseType(typeof(COLLECTION))]
        public IHttpActionResult GetCOLLECTION(int id)
        {
            COLLECTION cOLLECTION = db.COLLECTION.Find(id);
            if (cOLLECTION == null)
            {
                return NotFound();
            }

            return Ok(cOLLECTION);
        }

        // PUT: api/COLLECTION/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCOLLECTION(int id, COLLECTION cOLLECTION)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != cOLLECTION.id_collection)
            {
                return BadRequest();
            }

            db.Entry(cOLLECTION).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!COLLECTIONExists(id))
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

        // POST: api/COLLECTION
        [ResponseType(typeof(COLLECTION))]
        public IHttpActionResult PostCOLLECTION(COLLECTION cOLLECTION)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.COLLECTION.Add(cOLLECTION);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (COLLECTIONExists(cOLLECTION.id_collection))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = cOLLECTION.id_collection }, cOLLECTION);
        }

        // DELETE: api/COLLECTION/5
        [ResponseType(typeof(COLLECTION))]
        public IHttpActionResult DeleteCOLLECTION(int id)
        {
            COLLECTION cOLLECTION = db.COLLECTION.Find(id);
            if (cOLLECTION == null)
            {
                return NotFound();
            }

            db.COLLECTION.Remove(cOLLECTION);
            db.SaveChanges();

            return Ok(cOLLECTION);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool COLLECTIONExists(int id)
        {
            return db.COLLECTION.Count(e => e.id_collection == id) > 0;
        }
    }
}