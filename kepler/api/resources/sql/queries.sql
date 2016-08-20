-- :name get-parcels :? :*
-- :doc list all parcels
select * from parcel

-- :name add-parcel! :! :n
-- :doc add a new parcel to the kepler system
insert into parcel
(NAME, DESCRIPTION, ISDELIVERED, CREATEDBY, CREATEDON,  UPDATEDBY, UPDATEDON)
values (:name , :description, :isdelivered, :createdby, current_timestamp, :updatedby, current_timestamp )


-- :name add-location-for-parcel! :! :n
-- :doc add a new location for the parcel
insert into location 
(parcel_id, name , createdby, createon,geom)
values (:parcel_id, :name, :createdby, current_timestamp, :geom)

-- :name get-location-for-parcel :? :*
-- :doc get the last known location of the parcel
select p.name, p.description,  l.name, ST_AsText(l.geom)
from parcel p 
inner join location l on p.id = l.parcel_id
where p.id = :id


-- :name find-parcel-by-id :? :*
-- :doc get the a parcel given it's id
select * from parcel p where p.id = :id
