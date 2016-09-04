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
    public class PLANTPOTController : ApiController
    {
        private dbb_GudBoxEntities db = new dbb_GudBoxEntities();

        // GET: api/PLANTPOT
        public IQueryable<PLANTPOT> GetPLANTPOT()
        {
            return db.PLANTPOT;
        }

        // GET: api/PLANTPOT/5
        [ResponseType(typeof(PLANTPOT))]
        public IHttpActionResult GetPLANTPOT(int id)
        {
            PLANTPOT pLANTPOT = db.PLANTPOT.Find(id);
            if (pLANTPOT == null)
            {
                return NotFound();
            }

            return Ok(pLANTPOT);
        }

        // PUT: api/PLANTPOT/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPLANTPOT(int id, PLANTPOT pLANTPOT)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != pLANTPOT.id_plantpot)
            {
                return BadRequest();
            }

            db.Entry(pLANTPOT).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PLANTPOTExists(id))
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

        // POST: api/PLANTPOT
        [ResponseType(typeof(PLANTPOT))]
        public IHttpActionResult PostPLANTPOT(PLANTPOT pLANTPOT)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.PLANTPOT.Add(pLANTPOT);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (PLANTPOTExists(pLANTPOT.id_plantpot))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = pLANTPOT.id_plantpot }, pLANTPOT);
        }

        // DELETE: api/PLANTPOT/5
        [ResponseType(typeof(PLANTPOT))]
        public IHttpActionResult DeletePLANTPOT(int id)
        {
            PLANTPOT pLANTPOT = db.PLANTPOT.Find(id);
            if (pLANTPOT == null)
            {
                return NotFound();
            }

            db.PLANTPOT.Remove(pLANTPOT);
            db.SaveChanges();

            return Ok(pLANTPOT);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PLANTPOTExists(int id)
        {
            return db.PLANTPOT.Count(e => e.id_plantpot == id) > 0;
        }
    }
}