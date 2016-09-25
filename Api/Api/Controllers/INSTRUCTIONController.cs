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
    public class INSTRUCTIONController : ApiController
    {
        private dbb_GudBoxEntities db = new dbb_GudBoxEntities();

        // GET: api/INSTRUCTIONs
        public IQueryable<INSTRUCTION> GetINSTRUCTION()
        {
            return db.INSTRUCTION;
        }

        // GET: api/INSTRUCTIONs/5
        [ResponseType(typeof(INSTRUCTION))]
        public IHttpActionResult GetINSTRUCTION(int id)
        {
            INSTRUCTION iNSTRUCTION = db.INSTRUCTION.Find(id);
            if (iNSTRUCTION == null)
            {
                return NotFound();
            }

            return Ok(iNSTRUCTION);
        }

        // PUT: api/INSTRUCTIONs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutINSTRUCTION(int id, INSTRUCTION iNSTRUCTION)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != iNSTRUCTION.id_instruction)
            {
                return BadRequest();
            }

            db.Entry(iNSTRUCTION).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!INSTRUCTIONExists(id))
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

        // POST: api/INSTRUCTIONs
        [ResponseType(typeof(INSTRUCTION))]
        public IHttpActionResult PostINSTRUCTION(INSTRUCTION iNSTRUCTION)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.INSTRUCTION.Add(iNSTRUCTION);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (INSTRUCTIONExists(iNSTRUCTION.id_instruction))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = iNSTRUCTION.id_instruction }, iNSTRUCTION);
        }

        // DELETE: api/INSTRUCTIONs/5
        [ResponseType(typeof(INSTRUCTION))]
        public IHttpActionResult DeleteINSTRUCTION(int id)
        {
            INSTRUCTION iNSTRUCTION = db.INSTRUCTION.Find(id);
            if (iNSTRUCTION == null)
            {
                return NotFound();
            }

            db.INSTRUCTION.Remove(iNSTRUCTION);
            db.SaveChanges();

            return Ok(iNSTRUCTION);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool INSTRUCTIONExists(int id)
        {
            return db.INSTRUCTION.Count(e => e.id_instruction == id) > 0;
        }
    }
}