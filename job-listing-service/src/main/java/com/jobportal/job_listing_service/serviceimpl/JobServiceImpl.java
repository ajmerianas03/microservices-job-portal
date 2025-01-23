package com.jobportal.job_listing_service.serviceimpl;




import com.jobportal.job_listing_service.dto.JobDTO;
import com.jobportal.job_listing_service.exception.ResourceNotFoundException;
import com.jobportal.job_listing_service.mapper.JobMapper;
import com.jobportal.job_listing_service.model.Job;
import com.jobportal.job_listing_service.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class JobServiceImpl implements com.jobportal.joblisting.service.JobService {

    private final JobMapper jobMapper;


    private final JobRepository jobRepository;

    public JobServiceImpl(JobMapper jobMapper, JobRepository jobRepository) {
        this.jobMapper = jobMapper;
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDTO> getAllJobs() {
        try {
            List<Job> jobs = jobRepository.findAll();


            if (jobs.isEmpty()) {
                throw new ResourceNotFoundException("No jobs found in the database.");
            }

            return jobs.stream()
                    .map(jobMapper::jobToJobDTO)
                    .collect(Collectors.toList());

        } catch (ResourceNotFoundException ex) {

            throw ex;
        } catch (Exception ex) {

            throw new RuntimeException("An unexpected error occurred: " + ex.getMessage());
        }
    }




    @Override
    public JobDTO getJobById(Long id) {
        try {
            Job job = jobRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));

            return jobMapper.jobToJobDTO(job);

        } catch (ResourceNotFoundException ex) {
            throw ex;

        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred: " + ex.getMessage());
        }
    }


    @Override
    public JobDTO createJob(JobDTO jobDTO) {
        try {

            if (jobDTO.getTitle() == null || jobDTO.getDescription() == null) {
                throw new IllegalArgumentException("Job title and description must not be empty.");
            }

            Job job = jobMapper.jobDTOToJob(jobDTO);
            job = jobRepository.save(job);

            return jobMapper.jobToJobDTO(job);

        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while creating the job: " + ex.getMessage());
        }
    }



    @Override
    public void deleteJob(Long id) {
        try {
            Job existingJob = jobRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));

            jobRepository.delete(existingJob);

        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while deleting the job: " + ex.getMessage());
        }
    }

    @Override
    public JobDTO updateJob(Long id, JobDTO jobDTO) {
        try {
            Job existingJob = jobRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));


            existingJob.setTitle(jobDTO.getTitle());
            existingJob.setDescription(jobDTO.getDescription());


            existingJob = jobRepository.save(existingJob);

            return jobMapper.jobToJobDTO(existingJob);

        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while updating the job: " + ex.getMessage());
        }
    }

}
